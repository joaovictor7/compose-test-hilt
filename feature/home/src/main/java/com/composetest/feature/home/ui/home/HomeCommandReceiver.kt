package com.composetest.feature.home.ui.home

import com.composetest.core.domain.enums.Theme
import com.composetest.core.ui.interfaces.CommandReceiver

internal interface HomeCommandReceiver : CommandReceiver<HomeCommandReceiver> {
    fun navigateToHome2()
    fun changeAppTheme(theme: Theme? = null, dynamicColors: Boolean? = null)
}