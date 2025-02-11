package com.composetest.feature.home.navigation

import androidx.navigation.NavGraphBuilder
import com.composetest.core.router.destinations.home.HomeDestination
import com.composetest.core.ui.extensions.buildComposable
import com.composetest.feature.home.ui.home.HomeCommandReceiver
import com.composetest.feature.home.ui.home.HomeScreen
import com.composetest.feature.home.ui.home.HomeUiEvent
import com.composetest.feature.home.ui.home.HomeUiState
import com.composetest.feature.home.ui.home.HomeViewModel

fun NavGraphBuilder.homeRootNavGraph() {
    buildComposable<HomeDestination, HomeViewModel, HomeUiState, HomeUiEvent, HomeCommandReceiver>(
        screen = HomeScreen,
        navigateBackHandler = false
    )
}