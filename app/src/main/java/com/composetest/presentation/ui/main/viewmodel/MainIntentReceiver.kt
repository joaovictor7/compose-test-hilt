package com.composetest.presentation.ui.main.viewmodel

import com.composetest.core.ui.interfaces.IntentReceiver

internal interface MainIntentReceiver : IntentReceiver<MainIntentReceiver> {
    fun verifySession(currentRoute: String?)
    fun fetchRemoteConfig()
}