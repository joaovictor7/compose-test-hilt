package com.composetest.feature.configuration.ui.theme.analytics

import com.composetest.common.analytics.ClickAnalyticEvent

internal class ChangeDynamicColorsEvent(activeDynamicColors: Boolean) :
    ClickAnalyticEvent("dynamic_colors_active:$activeDynamicColors", ConfigurationThemeAnalytic)