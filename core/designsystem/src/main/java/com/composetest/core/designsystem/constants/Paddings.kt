package com.composetest.core.designsystem.constants

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.statusBars
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

internal val defaultMarginPadding = 16.dp

val topScreenPaddingList
    @Composable get() = PaddingValues(
        top = WindowInsets.statusBars.asPaddingValues().calculateTopPadding() + defaultMarginPadding
    )