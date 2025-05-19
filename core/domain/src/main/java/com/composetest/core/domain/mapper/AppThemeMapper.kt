package com.composetest.core.domain.mapper

import com.composetest.core.domain.enums.Theme
import com.composetest.core.domain.model.AppThemeModel
import com.composetest.core.domain.model.configuration.ThemeConfigurationModel
import javax.inject.Inject

internal class AppThemeMapper @Inject constructor() {
    fun mapperToModel(
        themeConfiguration: ThemeConfigurationModel,
        systemBarsTheme: Theme?
    ) = AppThemeModel(
        theme = themeConfiguration.theme,
        statusBarsTheme = systemBarsTheme ?: themeConfiguration.theme,
        dynamicColor = themeConfiguration.dynamicColor
    )
}