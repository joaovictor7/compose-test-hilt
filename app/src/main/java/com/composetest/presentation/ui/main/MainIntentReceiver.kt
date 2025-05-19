package com.composetest.presentation.ui.main

import com.composetest.core.ui.interfaces.IntentReceiver

internal interface MainIntentReceiver : IntentReceiver<MainIntentReceiver> {
    fun verifySession(currentRoute: String?)
    fun fetchRemoteConfig()
    fun dismissAlertDialog()
}