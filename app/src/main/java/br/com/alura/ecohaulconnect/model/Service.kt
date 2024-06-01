package br.com.alura.ecohaulconnect.model

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal
import java.time.LocalDate
import java.util.UUID

data class Service(
    // TODO: Integrar com identificador no formato da api
    val id: Long = 0,
    val description: String,
    val status: String,
    val value: BigDecimal,
    val date: LocalDate,
    val address: Address,
    val items: List<Item>,
    val category: String
)
