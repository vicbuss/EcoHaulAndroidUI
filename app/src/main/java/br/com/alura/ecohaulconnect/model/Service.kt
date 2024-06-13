package br.com.alura.ecohaulconnect.model

import br.com.alura.ecohaulconnect.extensions.toIsoLocalDateTime
import br.com.alura.ecohaulconnect.network.dtos.NewServiceData
import br.com.alura.ecohaulconnect.network.dtos.ServiceData
import java.math.BigDecimal
import java.time.LocalDate

data class Service(
    val id: Long = 0L,
    val description: String,
    val status: String,
    val value: BigDecimal,
    val date: LocalDate,
    val address: Address,
    val items: List<Item>,
    val category: String
)

fun Service.toNewServiceData(clientId: Long): NewServiceData {
    return NewServiceData(
        descricao = description,
        endereco = address.toAdressData(),
        valor = value,
        idCliente = clientId,
        dataAgendamento = date.toIsoLocalDateTime(),
        itens = items.map { item -> item.toItemData(category.uppercase()) }
    )
}

fun Service.toServiceData(clientId: Long): ServiceData {
    return ServiceData(
        id = id,
        descricao = description,
        endereco = address.toAdressData(),
        valor = value,
        idCliente = clientId,
        dataAgendamento = date.toIsoLocalDateTime(),
        itens = items.map { item -> item.toItemData(category.uppercase()) },
        ativo = status == "ativo"
    )
}