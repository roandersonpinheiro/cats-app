package com.example.catsapp.repository

import com.example.catsapp.api.ApiService

import javax.inject.Inject

class CatsRepository
    @Inject
    constructor(private val apiService: ApiService){
        suspend fun getFetchCats() = apiService.getFetchCats()
    }
