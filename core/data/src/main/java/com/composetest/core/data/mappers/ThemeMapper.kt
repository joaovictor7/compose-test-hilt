package com.composetest.core.data.mappers

import com.composetest.common.extensions.orFalse
import com.composetest.core.domain.enums.Theme
import com.composetest.core.domain.models.ThemeModel
import javax.inject.Inject

internal class ThemeMapper @Inject constructor() {

    operator fun invoke(
        theme: String?,
        dynamicColors: Boolean?
    ) = ThemeModel(
        theme = Theme.getThemeByName(theme),
        dynamicColors = dynamicColors.orFalse
    )

    operator fun invoke(
        theme: Theme?,
        statusBarsTheme: Theme?,
        dynamicColors: Boolean?
    ) = ThemeModel(
        theme = theme ?: Theme.AUTO,
        statusBarsTheme = statusBarsTheme ?: Theme.AUTO,
        dynamicColors = dynamicColors.orFalse,
    )
}