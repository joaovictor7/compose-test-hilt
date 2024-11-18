package com.composetest.feature.root.ui.root

import androidx.navigation.NavHostController
import com.composetest.core.ui.interfaces.CommandReceiver
import com.composetest.feature.root.enums.NavigationFeature
import com.composetest.feature.root.enums.NavigationLocal

internal interface RootCommandReceiver : CommandReceiver<RootCommandReceiver> {
    fun setSelectedNavigationFeature(
        selectedFeature: NavigationFeature,
        navigationLocal: NavigationLocal
    )
    fun setRootNavGraph(navController: NavHostController)
    fun backHandler()
}