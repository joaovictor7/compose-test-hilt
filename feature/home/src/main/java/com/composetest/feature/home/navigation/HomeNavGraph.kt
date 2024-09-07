package com.composetest.feature.home.navigation

import androidx.navigation.NavGraphBuilder
import com.composetest.core.router.destinations.home.Home2Destination
import com.composetest.core.router.destinations.home.HomeDestination
import com.composetest.core.router.extensions.composable
import com.composetest.feature.home.ui.home.HomeCommandReceiver
import com.composetest.feature.home.ui.home.HomeScreen
import com.composetest.feature.home.ui.home.HomeUiState
import com.composetest.feature.home.ui.home.HomeViewModel
import com.composetest.feature.home.ui.home2.Home2CommandReceiver
import com.composetest.feature.home.ui.home2.Home2Screen
import com.composetest.feature.home.ui.home2.Home2UiState
import com.composetest.feature.home.ui.home2.Home2ViewModel

fun NavGraphBuilder.homeRootNavGraph() {
    composable<HomeDestination, HomeViewModel, HomeUiState, HomeCommandReceiver>(HomeScreen)
    composable<Home2Destination, Home2ViewModel, Home2UiState, Home2CommandReceiver>(Home2Screen)
}