package com.composetest.feature.configuration.presenter.ui.theme

import com.composetest.core.ui.interfaces.IntentReceiver
import com.composetest.feature.configuration.presenter.enums.ThemeConfiguration

internal interface ThemeConfigurationIntentReceiver :
    IntentReceiver<ThemeConfigurationIntentReceiver> {

    fun changeTheme(selectedTheme: ThemeConfiguration)
    fun changeDynamicColor(active: Boolean)
}