package com.composetest.feature.root.dimensions

import androidx.compose.runtime.Composable
import com.composetest.core.designsystem.utils.getDensity
import com.composetest.feature.root.dimensions.densities.Component

internal val components: Component @Composable get() = getDensity(default = Component())