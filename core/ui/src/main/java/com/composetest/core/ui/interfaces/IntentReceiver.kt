package com.composetest.core.ui.interfaces

interface IntentReceiver<IntentReceiver> {
    val intentReceiver: IntentReceiver

    fun executeIntent(intent: Intent<IntentReceiver>) {
        intent.execute(intentReceiver)
    }
}