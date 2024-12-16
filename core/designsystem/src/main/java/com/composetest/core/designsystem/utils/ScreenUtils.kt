package com.composetest.core.designsystem.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalInspectionMode

val isPreview: Boolean @Composable get() = LocalInspectionMode.current