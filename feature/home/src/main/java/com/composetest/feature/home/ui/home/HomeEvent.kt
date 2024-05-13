package com.composetest.feature.home.ui.home

import com.composetest.core.ui.domain.enums.AppTheme

sealed interface HomeEvent {
    data object ReturnLogin: HomeEvent
    data class AppThemeHandle(
        val appTheme: AppTheme? = null,
        val dynamicColors: Boolean? = null
    ) : HomeEvent
}