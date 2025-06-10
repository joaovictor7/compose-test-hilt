package com.composetest.core.designsystem.enums.topbar

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

enum class TopBarColor {
    SURFACE, SURFACE_CONTAINER;

    internal companion object {
        @Composable
        fun TopBarColor.getColor() = when (this) {
            SURFACE -> MaterialTheme.colorScheme.surface
            SURFACE_CONTAINER -> MaterialTheme.colorScheme.surfaceContainer
        }
    }
}