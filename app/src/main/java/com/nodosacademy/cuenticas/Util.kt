package com.nodosacademy.cuenticas

import java.text.NumberFormat
import java.util.Currency

object Util {
    fun Double.toCOPMoney(): String {
        val format: NumberFormat = NumberFormat.getCurrencyInstance()
        format.setMaximumFractionDigits(0)
        format.currency = Currency.getInstance("COP")
        return format.format(this)
    }
}