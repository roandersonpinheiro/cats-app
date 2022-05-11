package com.example.catsapp.model

data class CatSearchResult(
    val data: List<CatResult>,
    val success: Boolean,
    val status: Int
)