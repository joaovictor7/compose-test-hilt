package com.composetest.core.designsystem.compositions

import androidx.compose.runtime.compositionLocalOf
import com.composetest.core.domain.enums.Theme

val LocalTheme = compositionLocalOf { Theme.AUTO }
