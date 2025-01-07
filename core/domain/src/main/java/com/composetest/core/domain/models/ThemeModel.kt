package com.composetest.core.domain.models

import com.composetest.core.domain.enums.Theme

data class ThemeModel(
    val theme: Theme = Theme.AUTO,
    val statusBarsTheme: Theme = theme,
    val dynamicColor: Boolean = false
)