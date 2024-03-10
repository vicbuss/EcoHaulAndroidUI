package br.com.alura.ecohaulconnect.model

data class Address(
    val street: String,
    val neighborhood: String,
    val zipCode: String,
    val city: String,
    val state: String,
    val number: String,
    val complement: String
)
