package com.composetest.feature.home.navigation

import androidx.navigation.NavGraphBuilder
import com.composetest.core.router.destinations.home.Home2Destination
import com.composetest.core.router.destinations.home.Home3Destination
import com.composetest.core.router.destinations.home.HomeDestination
import com.composetest.core.ui.extensions.composable
import com.composetest.feature.home.ui.home.HomeCommandReceiver
import com.composetest.feature.home.ui.home.HomeScreen
import com.composetest.feature.home.ui.home.HomeUiState
import com.composetest.feature.home.ui.home.HomeViewModel
import com.composetest.feature.home.ui.home2.Home2CommandReceiver
import com.composetest.feature.home.ui.home2.Home2Screen
import com.composetest.feature.home.ui.home2.Home2UiState
import com.composetest.feature.home.ui.home2.Home2ViewModel
import com.composetest.feature.home.ui.home3.Home3CommandReceiver
import com.composetest.feature.home.ui.home3.Home3Screen
import com.composetest.feature.home.ui.home3.Home3UiState
import com.composetest.feature.home.ui.home3.Home3ViewModel

fun NavGraphBuilder.homeRootNavGraph() {
    composable<HomeDestination, HomeViewModel, HomeUiState, HomeCommandReceiver>(
        screen = HomeScreen,
        navigateBackHandler = false
    )
    composable<Home2Destination, Home2ViewModel, Home2UiState, Home2CommandReceiver>(Home2Screen)
}

fun NavGraphBuilder.homeNavGraph() {
    composable<Home3Destination, Home3ViewModel, Home3UiState, Home3CommandReceiver>(Home3Screen)
}