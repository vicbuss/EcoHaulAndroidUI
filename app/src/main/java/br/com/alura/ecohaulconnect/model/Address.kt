package br.com.alura.ecohaulconnect.model

import com.google.gson.annotations.SerializedName

data class Address(
    @SerializedName("logradouro") val street: String,
    @SerializedName("bairro") val neighborhood: String,
    @SerializedName("cep") val zipCode: String,
    @SerializedName("cidade") val city: String,
    @SerializedName("uf") val state: String,
    @SerializedName("numero") val number: String,
    @SerializedName("complemento") val complement: String
)
