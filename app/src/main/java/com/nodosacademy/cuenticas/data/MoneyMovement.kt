package com.nodosacademy.cuenticas.data

data class MoneyMovement(
    val id: Int,
    val title: String,
    val value: Double,
    val isIncome: Boolean = false,
    val expenseType: MovementType,
)
