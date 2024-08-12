package com.composetest.feature.home.ui.home

import com.composetest.core.domain.enums.Theme
import com.composetest.core.ui.interfaces.Command

internal sealed interface HomeCommand : Command<HomeCommandReceiver> {
    data object NavigateToHome2 : HomeCommand {
        override fun execute(commandReceiver: HomeCommandReceiver) {
            commandReceiver.navigateToHome2()
        }
    }

    data class ChangeAppTheme(
        val theme: Theme? = null,
        val dynamicColors: Boolean? = null
    ) : HomeCommand {
        override fun execute(commandReceiver: HomeCommandReceiver) {
            commandReceiver.changeAppTheme(theme, dynamicColors)
        }
    }
}