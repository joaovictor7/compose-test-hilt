package com.composetest.presentation.ui.main.viewmodel

import androidx.navigation3.runtime.NavKey
import com.composetest.core.ui.interfaces.IntentReceiver

internal interface MainIntentReceiver : IntentReceiver<MainIntentReceiver> {
    fun verifySession(currentRoute: NavKey?)
    fun fetchRemoteConfig()
}