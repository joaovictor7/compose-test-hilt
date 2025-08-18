package com.composetest.core.designsystem.composition

import androidx.compose.runtime.compositionLocalOf
import com.composetest.core.domain.enums.Theme

val LocalAppTheme = compositionLocalOf { Theme.AUTO }
