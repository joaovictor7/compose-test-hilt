package com.composetest.feature.configuration.ui.theme

import com.composetest.feature.configuration.enums.ThemeConfiguration

internal data class ConfigurationThemeUiState(
    val themes: List<ThemeConfiguration> = emptyList(),
    val selectedTheme: ThemeConfiguration = ThemeConfiguration.DARK,
    val dynamicColor: Boolean = false
) {
    fun initUiState(
        themes: List<ThemeConfiguration>,
        theme: ThemeConfiguration,
        dynamicColor: Boolean
    ) = copy(
        themes = themes,
        selectedTheme = theme,
        dynamicColor = dynamicColor
    )

    fun setSelectedTheme(selectedTheme: ThemeConfiguration) = copy(selectedTheme = selectedTheme)
    fun setDynamicColors(active: Boolean) = copy(dynamicColor = active)
}
