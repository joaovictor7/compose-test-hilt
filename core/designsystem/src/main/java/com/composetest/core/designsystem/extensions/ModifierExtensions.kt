package com.composetest.core.designsystem.extensions

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import com.composetest.core.designsystem.dimensions.spacings

private val defaultPadding @Composable get() = spacings.eighteen

@Composable
fun Modifier.visibility(isVisible: Boolean) = alpha(if (isVisible) 1f else 0f)

@Composable
fun Modifier.verticalTopBackgroundBrush(isDarkMode: Boolean) = also {
    if (!isDarkMode) {
        val colorStops = arrayOf(
            0.1f to MaterialTheme.colorScheme.primary,
            0.99f to MaterialTheme.colorScheme.surface,
        )
        return background(brush = Brush.verticalGradient(colorStops = colorStops))
    }
}

@Composable
fun Modifier.defaultPaddings() = safeDrawingPadding().padding(defaultPadding)

@Composable
fun Modifier.defaultTopPadding() = statusBarsPadding().padding(top = defaultPadding)

@Composable
fun Modifier.defaultBottomPadding() = navigationBarsPadding().padding(bottom = defaultPadding)

@Composable
fun Modifier.defaultTopHorizontalPaddings() =
    defaultTopPadding().padding(horizontal = defaultPadding)