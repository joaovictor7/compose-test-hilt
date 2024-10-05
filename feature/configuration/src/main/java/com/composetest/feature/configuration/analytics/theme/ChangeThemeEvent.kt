package com.composetest.feature.configuration.analytics.theme

import com.composetest.core.domain.models.analytics.ClickAnalyticEvent

internal class ChangeThemeEvent(theme: String) :
    ClickAnalyticEvent("change_theme:$theme", ConfigurationThemeAnalytic)