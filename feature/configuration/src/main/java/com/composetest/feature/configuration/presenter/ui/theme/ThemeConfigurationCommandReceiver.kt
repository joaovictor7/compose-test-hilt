package com.composetest.feature.configuration.presenter.ui.theme

import com.composetest.core.ui.interfaces.CommandReceiver
import com.composetest.feature.configuration.presenter.enums.ThemeConfiguration

internal interface ThemeConfigurationCommandReceiver :
    CommandReceiver<ThemeConfigurationCommandReceiver> {

    fun changeTheme(selectedTheme: ThemeConfiguration)
    fun changeDynamicColor(active: Boolean)
}