package com.composetest.feature.configuration.ui.theme.analytics

import com.composetest.common.analytics.ClickAnalyticEvent

internal class ChangeThemeEvent(theme: String) :
    ClickAnalyticEvent("change_theme:$theme", ConfigurationThemeAnalytic)