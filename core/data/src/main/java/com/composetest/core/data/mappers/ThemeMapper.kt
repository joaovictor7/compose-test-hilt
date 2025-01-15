package com.composetest.core.data.mappers

import com.composetest.common.extensions.orFalse
import com.composetest.core.domain.enums.Theme
import com.composetest.core.domain.models.ThemeModel
import javax.inject.Inject

internal class ThemeMapper @Inject constructor() {

    operator fun invoke(
        theme: String?,
        dynamicColor: Boolean?
    ) = ThemeModel(
        theme = Theme.getThemeByName(theme),
        dynamicColor = dynamicColor.orFalse
    )

    operator fun invoke(
        theme: Theme?,
        statusBarsTheme: Theme?,
        dynamicColor: Boolean?
    ) = ThemeModel(
        theme = theme ?: Theme.AUTO,
        statusBarsTheme = statusBarsTheme ?: theme ?: Theme.AUTO,
        dynamicColor = dynamicColor.orFalse,
    )
}