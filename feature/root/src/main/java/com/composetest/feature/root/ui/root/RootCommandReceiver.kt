package com.composetest.feature.root.ui.root

import com.composetest.core.ui.interfaces.CommandReceiver
import com.composetest.feature.root.enums.NavigationFeature

internal interface RootCommandReceiver : CommandReceiver<RootCommandReceiver> {
    fun navigateToFeature(navigationFeature: NavigationFeature)
    fun backHandler()
    fun updateUserData()
    fun currentScreenObservable(currentRoute: String?)
    fun logout()
}