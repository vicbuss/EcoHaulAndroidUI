package br.com.alura.ecohaulconnect.model

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
