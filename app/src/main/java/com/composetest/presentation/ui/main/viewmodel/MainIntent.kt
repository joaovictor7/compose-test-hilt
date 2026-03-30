package com.composetest.presentation.ui.main.viewmodel

import androidx.navigation3.runtime.NavKey
import com.composetest.core.ui.interfaces.Intent

internal sealed interface MainIntent : Intent<MainIntentReceiver> {
    data class VerifySession(private val currentRoute: NavKey?) : MainIntent {
        override fun execute(intentReceiver: MainIntentReceiver) {
            intentReceiver.verifySession(currentRoute)
        }
    }

    data object FetchRemoteConfig : MainIntent {
        override fun execute(intentReceiver: MainIntentReceiver) {
            intentReceiver.fetchRemoteConfig()
        }
    }
}