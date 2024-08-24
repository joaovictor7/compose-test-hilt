package com.composetest.core.designsystem.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import com.composetest.core.designsystem.dimensions.densities.Densities
import com.composetest.core.designsystem.dimensions.densities.Densities.DEFAULT
import com.composetest.core.designsystem.dimensions.densities.Densities.SW_360
import com.composetest.core.designsystem.dimensions.densities.Densities.SW_600

@Composable
fun <T> getDensity(default: T, sw360: T? = null, sw600: T? = null) =
    when (getCurrentDensity()) {
        SW_360 -> sw360
        SW_600 -> sw600
        DEFAULT -> default
    } ?: default

@Composable
private fun getCurrentDensity(): Densities {
    val currentDensity = LocalConfiguration.current.screenWidthDp
    return when {
        currentDensity <= SW_360.density -> SW_360
        currentDensity <= SW_600.density -> SW_600
        else -> DEFAULT
    }
}