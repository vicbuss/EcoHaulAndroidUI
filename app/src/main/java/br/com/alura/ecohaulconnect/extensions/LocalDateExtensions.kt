package br.com.alura.ecohaulconnect.extensions

import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

fun LocalDate.toBrazilianDateFormat(): String {
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    return this.format(formatter)
}

fun LocalDate.toIsoLocalDateTime(): String {
    val localDateTime = this.atTime(LocalTime.MIDNIGHT)
    return localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
}

fun LocalDate.toUTCDate(): String {
    val formatter = DateTimeFormatter.ofPattern(("yyyy-MM-dd"))
    return this.format(formatter)
}