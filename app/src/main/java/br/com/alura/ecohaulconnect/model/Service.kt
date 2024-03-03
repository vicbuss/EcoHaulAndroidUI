package br.com.alura.ecohaulconnect.model

import java.math.BigDecimal
import java.time.LocalDate
import java.util.UUID

class Service(
    // TODO: Integrar com identificador no formato da api
    val id: String = UUID.randomUUID().toString(),
    val descricao: String,
    val status: String,
    val valor: BigDecimal,
    val data: LocalDate,
    val address: String,
    val items: List<Item>,
    val category: String
)
