package com.composetest.core.designsystem.extension

import androidx.annotation.FloatRange
import androidx.compose.ui.graphics.Color

fun Color.opacity(
    @FloatRange(from = 0.0, to = 1.0) value: Float
) = this.copy(alpha = value)