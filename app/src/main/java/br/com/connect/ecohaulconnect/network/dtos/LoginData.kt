package br.com.connect.ecohaulconnect.network.dtos

data class LoginBody(
    val login: String,
    val senha: String
)

data class LoginResponse(
    val token: String
)

data class UserIdBody(
    val email: String
)

data class UserIdResponse(
    val id: Long
)