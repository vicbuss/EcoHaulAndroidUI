package br.com.alura.ecohaulconnect.network.dtos

data class AddressData(
    val logradouro: String,
    val bairro: String,
    val cep: String,
    val numero: String,
    val complemento: String,
    val cidade: String,
    val uf: String
)