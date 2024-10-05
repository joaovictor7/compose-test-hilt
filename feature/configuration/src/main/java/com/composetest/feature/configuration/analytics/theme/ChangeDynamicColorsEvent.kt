package com.composetest.feature.configuration.analytics.theme

import com.composetest.core.domain.models.analytics.ClickAnalyticEvent

internal class ChangeDynamicColorsEvent(activeDynamicColors: Boolean) :
    ClickAnalyticEvent("dynamic_colors_active:$activeDynamicColors", ConfigurationThemeAnalytic)