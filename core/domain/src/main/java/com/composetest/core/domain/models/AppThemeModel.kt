package com.composetest.core.domain.models

import com.composetest.core.domain.enums.Theme

data class AppThemeModel(
    val theme: Theme = Theme.AUTO,
    val dynamicColors: Boolean = false,
    val customTheme: Theme? = null
)