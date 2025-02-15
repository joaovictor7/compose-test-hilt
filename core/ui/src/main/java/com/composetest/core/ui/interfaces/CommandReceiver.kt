package com.composetest.core.ui.interfaces

interface CommandReceiver<CommandReceiver> {
    val commandReceiver: CommandReceiver

    fun executeCommand(command: Command<CommandReceiver>) {
        command.execute(commandReceiver)
    }
}