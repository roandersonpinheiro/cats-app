package com.example.catsapp.model
data class CatResult(
    val id: String,
    val title: String,
    val description: String? = null,
    val datetime: Long,
    val link: String,
    val images: List<Image>? = null
)