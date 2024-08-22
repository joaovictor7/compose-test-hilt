package com.composetest.core.router.providers

import androidx.navigation.NavHostController
import com.composetest.core.router.enums.NavGraph

interface NavControllerProvider {
    fun setNavController(navGraph: NavGraph, navController: NavHostController)
    fun getNavController(navGraph: NavGraph): NavHostController
}