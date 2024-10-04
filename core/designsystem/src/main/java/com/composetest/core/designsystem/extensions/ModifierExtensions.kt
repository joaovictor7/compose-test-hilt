package com.composetest.core.designsystem.extensions

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import com.composetest.core.designsystem.compositions.LocalThemeProvider
import com.composetest.core.designsystem.constants.defaultMarginPadding

@Composable
fun Modifier.visibility(isVisible: Boolean) = alpha(if (isVisible) 1f else 0f)

@Composable
fun Modifier.verticalTopBackgroundBrush() = also {
    if (!LocalThemeProvider.current.isDarkMode) {
        val colorStops = arrayOf(
            0.1f to MaterialTheme.colorScheme.primary,
            0.99f to MaterialTheme.colorScheme.surface,
        )
        return background(brush = Brush.verticalGradient(colorStops = colorStops))
    }
}

@Composable
fun Modifier.horizontalScreenPadding() = padding(horizontal = defaultMarginPadding)

@Composable
fun Modifier.screenPadding() = windowInsetsPadding(WindowInsets.systemBars)
    .padding(top = defaultMarginPadding)
    .horizontalScreenPadding()
