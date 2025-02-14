package com.composetest.feature.home.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.composetest.core.router.destinations.home.HomeDestination
import com.composetest.feature.home.ui.home.HomeScreen
import com.composetest.feature.home.ui.home.HomeViewModel

fun NavGraphBuilder.rootHomeNavGraph() {
    composable<HomeDestination> {
        hiltViewModel<HomeViewModel>()
        HomeScreen()
    }
}