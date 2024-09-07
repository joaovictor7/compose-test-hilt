package com.composetest.core.router.managers

import androidx.navigation.NavHostController
import com.composetest.core.router.enums.NavGraph

interface NavControllerManager {
    fun setNavController(navGraph: NavGraph, navController: NavHostController)
    fun getNavController(navGraph: NavGraph): NavHostController
}