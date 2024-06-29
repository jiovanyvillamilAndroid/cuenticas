package com.nodosacademy.cuenticas.data

import com.nodosacademy.cuenticas.R

sealed class MovementType(val iconResId: Int) {
    data object Food : MovementType(iconResId = R.drawable.ic_launcher_background)
    data object Entertainment : MovementType(iconResId = R.drawable.ic_launcher_background)
}