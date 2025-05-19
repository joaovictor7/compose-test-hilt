package com.composetest.feature.root.presentation.ui.root

import com.composetest.core.ui.interfaces.IntentReceiver
import com.composetest.feature.root.presentation.enums.NavigationFeature

internal interface RootIntentReceiver : IntentReceiver<RootIntentReceiver> {
    fun navigateToFeature(navigationFeature: NavigationFeature)
    fun backHandler()
    fun updateUserData()
    fun currentScreenObservable(currentRoute: String?)
    fun logout()
}