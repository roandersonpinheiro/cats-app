package com.example.catsapp.api
import com.example.catsapp.model.CatSearchResult
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("3/gallery/search/?q=cats")
    fun getFetchCats(): Call<CatSearchResult>
}