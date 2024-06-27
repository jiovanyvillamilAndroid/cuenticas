package com.nodosacademy.cuenticas.data

import com.nodosacademy.cuenticas.ExpenseType

data class Expense(
    val id: Int,
    val title: String,
    val value: Double,
    val expenseType: ExpenseType,
)
