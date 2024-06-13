package br.com.alura.ecohaulconnect.network.dtos

data class UserData(
    val nome: String,
    val telefone: String,
    val email: String,
    val cpf: String,
    val dataNascimento: String,
    val endereco: AddressData
)