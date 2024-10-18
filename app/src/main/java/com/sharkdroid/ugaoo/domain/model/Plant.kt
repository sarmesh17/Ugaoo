package com.sharkdroid.ugaoo.domain.model

data class Plant(
    val name: String = "",
    val price: String = "",
    val type: String = "",  // "Indoor" or "Outdoor"
    val imageUrl: String = ""  // URL of the uploaded image
)
