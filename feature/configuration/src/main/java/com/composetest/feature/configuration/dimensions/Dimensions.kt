package com.composetest.feature.configuration.dimensions

import androidx.compose.runtime.Composable
import com.composetest.core.designsystem.utils.getDensity
import com.composetest.feature.configuration.dimensions.densities.Component

internal val components: Component @Composable get() = getDensity(default = Component())