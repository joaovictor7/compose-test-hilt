package com.composetest.feature.configuration.data.mapper

import com.composetest.common.extension.orFalse
import com.composetest.core.domain.enums.Theme
import com.composetest.core.domain.model.ThemeConfigurationModel
import javax.inject.Inject

internal class ThemeConfigurationMapper @Inject constructor() {

    fun mapperToModel(theme: String?, dynamicColor: Boolean?) =
        ThemeConfigurationModel(
            theme = Theme.getThemeByName(theme),
            dynamicColor = dynamicColor.orFalse
        )
}