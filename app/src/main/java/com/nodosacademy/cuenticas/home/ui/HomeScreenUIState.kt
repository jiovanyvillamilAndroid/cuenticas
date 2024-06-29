package com.nodosacademy.cuenticas.home.ui

import com.nodosacademy.cuenticas.data.MoneyMovement

data class HomeScreenUIState(
    val userName: String = "",
    val userId: String = "",
    val balance: Double = 0.0,
    val hasNotification: Boolean = false,
    val list: List<MoneyMovement> = arrayListOf()
)