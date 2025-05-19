package com.composetest.feature.root.presentation.ui.root

import com.composetest.core.ui.interfaces.Intent
import com.composetest.feature.root.presentation.enums.NavigationFeature

internal sealed interface RootIntent : Intent<RootIntentReceiver> {
    data class NavigateToFeature(private val navigationFeature: NavigationFeature) : RootIntent {
        override fun execute(intentReceiver: RootIntentReceiver) {
            intentReceiver.navigateToFeature(navigationFeature)
        }
    }

    data class CurrentScreenObservable(private val currentScreen: String?) : RootIntent {
        override fun execute(intentReceiver: RootIntentReceiver) {
            intentReceiver.currentScreenObservable(currentScreen)
        }
    }

    data object UpdateUserData : RootIntent {
        override fun execute(intentReceiver: RootIntentReceiver) {
            intentReceiver.updateUserData()
        }
    }

    data object BackHandler : RootIntent {
        override fun execute(intentReceiver: RootIntentReceiver) {
            intentReceiver.backHandler()
        }
    }

    data object Logout : RootIntent {
        override fun execute(intentReceiver: RootIntentReceiver) {
            intentReceiver.logout()
        }
    }
}