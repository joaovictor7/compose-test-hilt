package com.composetest.core.designsystem.dimension

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.statusBars
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

val screenMargin = 16.dp

val topScreenMarginList
    @Composable get() = PaddingValues(
        top = WindowInsets.statusBars.asPaddingValues().calculateTopPadding() + screenMargin
    )