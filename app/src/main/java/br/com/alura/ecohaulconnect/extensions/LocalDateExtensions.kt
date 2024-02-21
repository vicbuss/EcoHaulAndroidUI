package br.com.alura.ecohaulconnect.extensions

import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun LocalDate.toBrazilianDateFormat(): String {
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    return this.format(formatter)
}