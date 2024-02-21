package br.com.alura.ecohaulconnect.sampledata

import br.com.alura.ecohaulconnect.model.Service
import java.math.BigDecimal
import java.time.LocalDate

val sampleService = Service(
    descricao = "Um sofá de dois lugares para descartar",
    valor = BigDecimal("250.00"),
    status = "ativo",
    data = LocalDate.parse("2023-10-19")
)