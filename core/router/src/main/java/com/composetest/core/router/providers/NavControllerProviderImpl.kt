package com.composetest.core.router.providers

import androidx.navigation.NavHostController
import com.composetest.core.router.enums.NavGraph
import javax.inject.Inject
import kotlin.collections.MutableMap
import kotlin.collections.mutableMapOf
import kotlin.collections.set

internal class NavControllerProviderImpl @Inject constructor() : NavControllerProvider {

    private var navControllers: MutableMap<NavGraph, NavHostController> = mutableMapOf()

    override fun setNavController(navGraph: NavGraph, navController: NavHostController) {
        navControllers[navGraph] = navController
    }

    override fun getNavController(navGraph: NavGraph) =
        checkNotNull(navControllers[navGraph])
}