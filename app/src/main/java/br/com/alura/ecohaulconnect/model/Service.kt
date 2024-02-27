package br.com.alura.ecohaulconnect.model

import java.math.BigDecimal
import java.time.LocalDate

class Service(
    val descricao: String,
    val status: String,
    val valor: BigDecimal,
    val data: LocalDate,
    val address: String,
    val items: List<Item>,
    val category: String
)
