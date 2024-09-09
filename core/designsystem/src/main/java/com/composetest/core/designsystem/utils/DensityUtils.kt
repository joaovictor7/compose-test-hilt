package com.composetest.core.designsystem.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import com.composetest.core.designsystem.dimensions.densities.Densities
import com.composetest.core.designsystem.dimensions.densities.Densities.DEFAULT
import com.composetest.core.designsystem.dimensions.densities.Densities.DP_1200
import com.composetest.core.designsystem.dimensions.densities.Densities.DP_840

@Composable
private fun getCurrentDensity(): Densities {
    val currentDensity = LocalConfiguration.current.screenWidthDp
    return when {
        currentDensity <= DP_840.density -> DP_840
        currentDensity <= DP_1200.density -> DP_1200
        else -> DEFAULT
    }
}

@Composable
fun <T> getDensity(default: T, dp840: T? = null, dp1200: T? = null) =
    when (getCurrentDensity()) {
        DP_840 -> dp840
        DP_1200 -> dp1200
        DEFAULT -> default
    } ?: default