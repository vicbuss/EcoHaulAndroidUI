package br.com.alura.ecohaulconnect.model

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal
import java.time.LocalDate
import java.util.UUID

data class Service(
    // TODO: Integrar com identificador no formato da api
    @SerializedName("idServico") val id: String = UUID.randomUUID().toString(),
    @SerializedName("descricao") val description: String,
    @SerializedName("status") val status: String,
    @SerializedName("valor") val value: BigDecimal,
    @SerializedName("dataAgendamento") val date: LocalDate,
    @SerializedName("endereco") val address: Address,
    @SerializedName("itens") val items: List<Item>,
    @SerializedName("tipo") val category: String
)
