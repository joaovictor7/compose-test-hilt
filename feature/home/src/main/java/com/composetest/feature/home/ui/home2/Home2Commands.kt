package com.composetest.feature.home.ui.home2

import com.composetest.core.ui.interfaces.Command

internal sealed interface Home2Commands : Command<Home2CommandReceiver> {
    data object ReturnHome : Home2Commands {
        override fun execute(receiver: Home2CommandReceiver) {
            receiver.returnHome()
        }
    }
}