package br.com.alura.ecohaulconnect.model

import com.google.gson.annotations.SerializedName

data class Address(
    val street: String,
    val neighborhood: String,
    val zipCode: String,
    val city: String,
    val state: String,
    val number: String,
    val complement: String
)
