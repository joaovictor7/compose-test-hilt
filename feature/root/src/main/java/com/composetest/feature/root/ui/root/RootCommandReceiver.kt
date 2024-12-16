package com.composetest.feature.root.ui.root

import androidx.compose.material3.DrawerValue
import androidx.navigation.NavHostController
import com.composetest.core.ui.interfaces.CommandReceiver
import com.composetest.feature.root.enums.NavigationFeature

internal interface RootCommandReceiver : CommandReceiver<RootCommandReceiver> {
    fun navigateToFeature(feature: NavigationFeature)
    fun setRootNavGraph(navController: NavHostController)
    fun backHandler()
    fun modalDrawerManager(drawerValue: DrawerValue)
}