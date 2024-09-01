package com.composetest.core.router.managers

import androidx.navigation.NavHostController
import com.composetest.core.router.enums.NavGraph

interface NavControllerManager {
    /**
     * @return Returns `true` in case of [navGraph] already exists and was replaced,
     * `false` if only added
     */
    fun setNavController(navGraph: NavGraph, navController: NavHostController): Boolean
    fun getNavController(navGraph: NavGraph): NavHostController
}