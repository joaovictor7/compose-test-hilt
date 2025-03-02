package com.composetest.core.data.mappers

import com.composetest.common.extensions.orFalse
import com.composetest.core.domain.enums.Theme
import com.composetest.core.domain.models.configuration.ThemeConfigurationModel
import javax.inject.Inject

internal class ThemeConfigurationMapper @Inject constructor() {

    fun mapperToModel(theme: String?, dynamicColor: Boolean?) = ThemeConfigurationModel(
        theme = Theme.getThemeByName(theme),
        dynamicColor = dynamicColor.orFalse
    )
}