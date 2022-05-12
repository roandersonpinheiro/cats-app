package com.example.catsapp.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.catsapp.model.Image
import com.example.catsapp.repository.CatsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class CatsFragmentViewModel
@Inject
constructor(private val repository: CatsRepository) : ViewModel() {
    companion object {
        const val IMAGE_SIZE = 't'
    }
    private val images = mutableListOf<Image>()
    val errorMessage = MutableLiveData<String>()
    val catListImages = MutableLiveData<List<Image>>()
    val loading = MutableLiveData<Boolean>()
    private var job: Job? = null

    init {
        getFetchCats()
    }
    fun getFetchCats() {
        job = CoroutineScope(Dispatchers.IO).launch {
            try {
                loading.postValue(true)
                val requestCat = repository.getFetchCats()
                val responseCat = requestCat.execute()
                withContext(Dispatchers.Main) {
                    if (responseCat.isSuccessful) {
                        responseCat.body()?.data?.forEach { item ->
                            item.images?.forEach { image ->
                                if (image.link != null) {
                                    val idx = image.link.lastIndexOf(".")
                                    image.thumbLink = StringBuilder(image.link).insert(
                                        idx,
                                        IMAGE_SIZE
                                    ).toString()
                                    images.add(image)
                                }
                            }
                        }
                        catListImages.postValue(images)
                        loading.value = false
                    } else {
                        catListImages.postValue(emptyList())
                        onError("Error : ${responseCat.errorBody()} ")
                    }
                }

            }catch(e: Exception){
                catListImages.postValue(emptyList())
                errorMessage.value = ""
            }
        }

    }

    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}