package com.composetest.core.designsystem.dimensions.densities

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration

internal enum class Densities(private val density: Int = 0) {
    DEFAULT,
    SW_360(360),
    SW_600(600);

    companion object {
        @Composable
        private fun getCurrentDensity(): Densities {
            val currentDensity = LocalConfiguration.current.screenWidthDp
            return when {
                currentDensity <= SW_360.density -> SW_360
                currentDensity <= SW_600.density -> SW_600
                else -> DEFAULT
            }
        }

        @Composable
        fun <T> getDensity(default: T, sw360: T? = null, sw600: T? = null) =
            when (getCurrentDensity()) {
                SW_360 -> sw360
                SW_600 -> sw600
                DEFAULT -> default
            } ?: default
    }
}