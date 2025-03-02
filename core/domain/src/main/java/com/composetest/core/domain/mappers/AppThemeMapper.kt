package com.composetest.core.domain.mappers

import com.composetest.core.domain.enums.Theme
import com.composetest.core.domain.models.AppThemeModel
import com.composetest.core.domain.models.configuration.ThemeConfigurationModel
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