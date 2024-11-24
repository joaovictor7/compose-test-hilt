package com.composetest.ui

import androidx.navigation.NavHostController
import com.composetest.core.ui.interfaces.CommandReceiver

internal interface MainCommandReceiver : CommandReceiver<MainCommandReceiver> {
    fun verifySession()
    fun fetchRemoteConfig()
    fun setMainNavGraph(navController: NavHostController)
}