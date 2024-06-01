package br.com.alura.ecohaulconnect.model

import com.google.gson.annotations.SerializedName

data class Item(
    val pictureLinks: List<String>,
    val description: String,
    val heightInCm: Int,
    val widthInCm: Int,
    val lengthInCm: Int,
    val weightInKilograms: Int
)
