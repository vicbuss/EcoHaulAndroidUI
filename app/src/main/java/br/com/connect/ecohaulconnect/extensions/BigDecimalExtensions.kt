package br.com.connect.ecohaulconnect.extensions

import java.math.BigDecimal
import java.text.NumberFormat
import java.util.Locale

fun BigDecimal.toBrazilianCurrency(): String {
    return NumberFormat.getCurrencyInstance(Locale("pt", "br")).format(this)
}