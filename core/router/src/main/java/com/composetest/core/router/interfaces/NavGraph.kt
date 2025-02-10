package com.composetest.core.router.interfaces

import androidx.navigation.NavGraphBuilder

interface NavGraph {
    fun NavGraphBuilder.navGraph(navigateBackHandler: Boolean = true)
}