package br.com.alura.ecohaulconnect.model

import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("linkDeFotos") val pictureLinks: List<String>,
    @SerializedName("descricao") val description: String,
    @SerializedName("alturaEmCm") val heightInCm: Int,
    @SerializedName("larguraEmCm") val widthInCm: Int,
    @SerializedName("comprimentoEmCm") val lengthInCm: Int,
    @SerializedName("pesoEmQuilogramas") val weightInKilograms: Int
)
