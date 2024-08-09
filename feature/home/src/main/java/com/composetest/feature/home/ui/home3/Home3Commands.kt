package com.composetest.feature.home.ui.home3

import com.composetest.core.ui.interfaces.Command

internal sealed interface Home3Commands : Command<Home3CommandReceiver> {
    data object ReturnLogin : Home3Commands {
        override fun execute(receiver: Home3CommandReceiver) {
            receiver.returnLogin()
        }
    }
}