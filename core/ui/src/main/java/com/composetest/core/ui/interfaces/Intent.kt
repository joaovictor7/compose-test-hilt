package com.composetest.core.ui.interfaces

interface Intent<IntentReceiver> {
    fun execute(intentReceiver: IntentReceiver)
}