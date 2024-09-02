package com.composetest.feature.home.ui.home

import com.composetest.core.ui.interfaces.Command

internal sealed interface HomeCommand : Command<HomeCommandReceiver> {
    data object NavigateToHome2 : HomeCommand {
        override fun execute(commandReceiver: HomeCommandReceiver) {
            commandReceiver.navigateToHome2()
        }
    }
}