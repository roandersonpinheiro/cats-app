package com.example.catsapp.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.catsapp.model.CatSearchResult
import com.example.catsapp.repository.CatsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class CatsFragmentViewModel
@Inject
constructor(private val repository: CatsRepository) : ViewModel() {
    val errorMessage = MutableLiveData<String>()
    val catList = MutableLiveData<CatSearchResult>()
    val loading = MutableLiveData<Boolean>()
    private var job: Job? = null

    init {
        getFetchCats()
    }

    fun getFetchCats() {
        job = CoroutineScope(Dispatchers.IO).launch {
            loading.postValue(true)
            val requestCat = repository.getFetchCats()
            val responseCat = requestCat.execute()
            withContext(Dispatchers.Main) {
                if (responseCat.isSuccessful) {
                    catList.postValue(responseCat.body())
                    loading.value = false
                } else {
                    onError("Error : ${responseCat.errorBody()} ")
                }
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