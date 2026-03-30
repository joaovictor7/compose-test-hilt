package com.composetest.feature.root.presentation.ui.root.viewmodel

import androidx.navigation3.runtime.NavKey
import com.composetest.core.ui.interfaces.IntentReceiver
import com.composetest.feature.root.presentation.enums.NavigationFeature

internal interface RootIntentReceiver : IntentReceiver<RootIntentReceiver> {
    fun navigateToFeature(navigationFeature: NavigationFeature)
    fun backHandler()
    fun updateUserData()
    fun currentScreenObservable(currentRoute: NavKey?)
    fun logout()
}