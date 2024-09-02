package com.composetest.feature.home.ui.home2

import com.composetest.core.ui.interfaces.Command

internal sealed interface Home2Command : Command<Home2CommandReceiver> {
    data object ReturnHome : Home2Command {
        override fun execute(commandReceiver: Home2CommandReceiver) {
            commandReceiver.returnHome()
        }
    }
}