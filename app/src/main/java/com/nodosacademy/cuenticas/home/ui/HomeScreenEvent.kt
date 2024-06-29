package com.nodosacademy.cuenticas.home.ui

import com.nodosacademy.cuenticas.data.MoneyMovement

sealed class HomeScreenEvent {
    data class OnAddExpense(val expense: MoneyMovement) : HomeScreenEvent()
    data class OnAddIncome(val income: MoneyMovement) : HomeScreenEvent()
    data object OnNotificationClick : HomeScreenEvent()
}