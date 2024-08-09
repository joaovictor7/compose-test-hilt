package com.composetest.core.data.mappers

import com.composetest.common.extensions.orFalse
import com.composetest.core.domain.enums.Theme
import com.composetest.core.domain.models.AppThemeModel
import javax.inject.Inject

internal class AppThemeMapper @Inject constructor() {

    operator fun invoke(
        theme: String?,
        dynamicColor: Boolean?
    ) = AppThemeModel(
        theme = Theme.getThemeByName(theme),
        dynamicColors = dynamicColor.orFalse
    )
}