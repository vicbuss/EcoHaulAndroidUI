package br.com.alura.ecohaulconnect.model

import br.com.alura.ecohaulconnect.network.dtos.AddressData

data class Address(
    val street: String,
    val neighborhood: String,
    val zipCode: String,
    val city: String,
    val state: String,
    val number: String,
    val complement: String
)

fun Address.toAdressData(): AddressData {
    return AddressData(
        logradouro = street,
        bairro = neighborhood,
        cep = zipCode,
        cidade = city,
        uf = state,
        numero = number,
        complemento = complement
    )
}
