package com.composetest.feature.configuration.analytics.theme

import com.composetest.core.domain.analytics.ClickAnalyticEvent

internal sealed class ConfigurationThemeClickEventAnalytic(
    event: String
) : ClickAnalyticEvent(event, ConfigurationThemeScreenAnalytic) {
    data class ChangeDynamicColors(
        private val activeDynamicColors: Boolean
    ) : ConfigurationThemeClickEventAnalytic("dynamic_colors_active:$activeDynamicColors")

    data class ChangeThemeEvent(
        private val theme: String
    ) : ConfigurationThemeClickEventAnalytic("change_theme:$theme")
}