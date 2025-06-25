package com.composetest.feature.home.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.composetest.core.router.destination.home.HomeDestination
import com.composetest.core.router.interfaces.NavGraph
import com.composetest.feature.home.presenter.ui.home.HomeScreen
import com.composetest.feature.home.presenter.ui.home.HomeViewModel

object RootHomeNavGraph : NavGraph {
    override fun NavGraphBuilder.register(
        navController: NavHostController
    ) {
        composable<HomeDestination> {
            hiltViewModel<HomeViewModel>()
            HomeScreen()
        }
    }
}