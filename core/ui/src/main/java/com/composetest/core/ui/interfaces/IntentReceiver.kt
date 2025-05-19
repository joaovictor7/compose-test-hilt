package com.composetest.core.ui.interfaces

interface IntentReceiver<IntentReceiver> {
    val intentReceiver: IntentReceiver

    fun executeCommand(intent: Intent<IntentReceiver>) {
        intent.execute(intentReceiver)
    }
}