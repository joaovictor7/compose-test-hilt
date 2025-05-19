package com.composetest.presentation.ui.main

import com.composetest.core.ui.interfaces.Intent

internal sealed interface MainIntent : Intent<MainIntentReceiver> {
    data class VerifySession(private val currentRoute: String?) : MainIntent {
        override fun execute(intentReceiver: MainIntentReceiver) {
            intentReceiver.verifySession(currentRoute)
        }
    }

    data object FetchRemoteConfig : MainIntent {
        override fun execute(intentReceiver: MainIntentReceiver) {
            intentReceiver.fetchRemoteConfig()
        }
    }

    data object DismissAlertDialog : MainIntent {
        override fun execute(intentReceiver: MainIntentReceiver) {
            intentReceiver.dismissAlertDialog()
        }
    }
}