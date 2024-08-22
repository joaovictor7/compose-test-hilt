package com.composetest.feature.root.ui

import androidx.navigation.NavHostController
import com.composetest.core.ui.interfaces.CommandReceiver

internal interface RootCommandReceiver : CommandReceiver<RootCommandReceiver> {
    fun changeDockItemSelected(selectedIndex: Int)
    fun setRootNavGraph(navController: NavHostController)
}