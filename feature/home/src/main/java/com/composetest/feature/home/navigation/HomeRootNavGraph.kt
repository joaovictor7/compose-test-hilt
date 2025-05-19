package com.composetest.feature.home.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.composetest.core.router.destination.home.HomeDestination
import com.composetest.feature.home.presenter.ui.home.HomeScreen
import com.composetest.feature.home.presenter.ui.home.HomeViewModel

fun NavGraphBuilder.rootHomeNavGraph() {
    composable<HomeDestination> {
        hiltViewModel<HomeViewModel>()
        HomeScreen()
    }
}