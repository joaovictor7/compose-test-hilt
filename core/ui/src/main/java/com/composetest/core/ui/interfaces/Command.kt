package com.composetest.core.ui.interfaces

interface Command<CommandReceiver> {
    fun execute(commandReceiver: CommandReceiver)
}