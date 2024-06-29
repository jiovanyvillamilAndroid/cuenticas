package com.nodosacademy.cuenticas.home.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.nodosacademy.cuenticas.home.ui.HomeScreenEvent
import com.nodosacademy.cuenticas.home.ui.HomeScreenUIState

class HomeViewModel : ViewModel() {

    var state by mutableStateOf(HomeScreenUIState())
        private set

    fun onEvent(event: HomeScreenEvent) {
        state = when (event) {
            is HomeScreenEvent.OnAddExpense -> {
                state.copy(balance = state.balance - event.expense.value)
            }

            is HomeScreenEvent.OnAddIncome -> {
                state.copy(balance = state.balance + event.income.value)
            }

            HomeScreenEvent.OnNotificationClick -> {
                state.copy(hasNotification = !state.hasNotification)
            }
        }
    }
}