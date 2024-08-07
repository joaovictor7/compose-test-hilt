package com.composetest.feature.home.ui.home

import com.composetest.common.enums.Theme
import com.composetest.core.ui.interfaces.Command

internal sealed interface HomeCommands : Command<HomeCommandReceiver> {
    data object NavigateToHome2: HomeCommands {
        override fun execute(receiver: HomeCommandReceiver) {
            receiver.navigateToHome2()
        }
    }

    data class ChangeAppTheme(
        val theme: Theme? = null,
        val dynamicColors: Boolean? = null
    ) : HomeCommands {
        override fun execute(receiver: HomeCommandReceiver) {
            receiver.changeAppTheme(theme, dynamicColors)
        }
    }
}