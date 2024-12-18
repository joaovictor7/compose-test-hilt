package com.composetest.feature.configuration.ui.theme

import com.composetest.core.ui.interfaces.CommandReceiver
import com.composetest.feature.configuration.enums.ThemeConfiguration

internal interface ConfigurationThemeCommandReceiver :
    CommandReceiver<ConfigurationThemeCommandReceiver> {

    fun changeTheme(selectedTheme: ThemeConfiguration)
    fun changeDynamicColor(active: Boolean)
}