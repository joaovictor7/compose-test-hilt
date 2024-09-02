package com.composetest.feature.root.ui

import androidx.navigation.NavHostController
import com.composetest.core.ui.interfaces.CommandReceiver

internal interface RootCommandReceiver : CommandReceiver<RootCommandReceiver> {
    fun changeSelectedDockItem(selectedIndex: Int)
    fun setRootNavGraph(navController: NavHostController)
    fun setDockHeight(height: Int)
    fun backHandler()
}