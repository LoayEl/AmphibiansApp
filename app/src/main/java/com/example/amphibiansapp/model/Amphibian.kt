package com.example.amphibiansapp.model

import com.squareup.moshi.Json
import kotlinx.serialization.SerialName

data class Amphibian(
    val name: String,
    val type: String,
    val description: String,
    @SerialName(value = "img_src")
    val imageUrl: String
)
