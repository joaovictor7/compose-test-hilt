package com.composetest.core.router.interfaces

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

interface NavGraph {
    fun NavGraphBuilder.register(navController: NavHostController)
}