package br.com.connect.ecohaulconnect.extensions

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun String.fromUtcStringToLocalDate(): LocalDate {
    val formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME
    val localDateTime = LocalDateTime.parse(this, formatter)
    return localDateTime.toLocalDate()
}

fun String.fromBrazilianDateToLocalDate(): LocalDate {
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    return LocalDate.parse(this, formatter)
}