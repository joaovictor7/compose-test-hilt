package com.composetest.ui

import com.composetest.core.ui.interfaces.Command

internal class VerifySession : Command<MainCommandReceiver> {
    override fun execute(commandReceiver: MainCommandReceiver) {
        commandReceiver.verifySession()
    }
}