package com.composetest.core.domain.model

import com.composetest.core.domain.enums.Theme

data class AppThemeModel(
    val theme: Theme = Theme.AUTO,
    val statusBarsTheme: Theme = theme,
    val dynamicColor: Boolean = false
)