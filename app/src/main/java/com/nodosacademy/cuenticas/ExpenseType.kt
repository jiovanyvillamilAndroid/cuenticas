package com.nodosacademy.cuenticas

sealed class ExpenseType(val iconResId: Int) {
    class Food : ExpenseType(iconResId = R.drawable.ic_launcher_background)
    class Entertainment : ExpenseType( iconResId = R.drawable.ic_launcher_background)
}