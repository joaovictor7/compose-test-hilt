package com.composetest.core.designsystem.extensions

import androidx.compose.ui.graphics.Color

fun Color.opacity(value: Float) = this.copy(
    alpha = when {
        value >= 1f -> 1f
        value <= 0f -> 0f
        else -> value
    }
)