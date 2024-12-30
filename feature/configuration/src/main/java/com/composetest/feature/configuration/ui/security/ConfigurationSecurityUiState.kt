package com.composetest.feature.configuration.ui.security

import com.composetest.core.ui.interfaces.BaseUiState
import com.composetest.feature.configuration.enums.ThemeConfiguration

internal data class ConfigurationSecurityUiState(
    val themes: List<ThemeConfiguration> = emptyList(),
) : BaseUiState {
    fun initUiState(
        themes: List<ThemeConfiguration>,
        theme: ThemeConfiguration,
        dynamicColors: Boolean
    ) = copy(
        themes = themes,
    )

}
