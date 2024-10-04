package com.composetest.feature.root.ui

import androidx.navigation.NavHostController
import com.composetest.core.ui.interfaces.CommandReceiver
import com.composetest.feature.root.enums.NavigationBottomBar

internal interface RootCommandReceiver : CommandReceiver<RootCommandReceiver> {
    fun setSelectedNavigationBottomBar(selectedBottomBarItem: NavigationBottomBar)
    fun setRootNavGraph(navController: NavHostController)
    fun backHandler()
}