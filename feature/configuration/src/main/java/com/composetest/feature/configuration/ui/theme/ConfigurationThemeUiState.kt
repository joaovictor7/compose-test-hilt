package com.composetest.feature.configuration.ui.theme

import com.composetest.core.ui.interfaces.BaseUiState
import com.composetest.feature.configuration.enums.ThemeConfiguration

internal data class ConfigurationThemeUiState(
    val themes: List<ThemeConfiguration> = emptyList(),
    val selectedTheme: ThemeConfiguration = ThemeConfiguration.DARK,
    val dynamicColors: Boolean = false
) : BaseUiState {
    fun initUiState(
        themes: List<ThemeConfiguration>,
        theme: ThemeConfiguration,
        dynamicColors: Boolean
    ) = copy(
        themes = themes,
        selectedTheme = theme,
        dynamicColors = dynamicColors
    )
    fun setSelectedTheme(selectedTheme: ThemeConfiguration) = copy(selectedTheme = selectedTheme)
    fun setDynamicColors(active: Boolean) = copy(dynamicColors = active)
}
