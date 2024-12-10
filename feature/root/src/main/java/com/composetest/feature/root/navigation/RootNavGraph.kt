package com.composetest.feature.root.navigation

import androidx.navigation.NavGraphBuilder
import com.composetest.core.router.destinations.root.RootDestination
import com.composetest.core.ui.extensions.composable
import com.composetest.core.ui.extensions.composable2
import com.composetest.feature.root.ui.root.RootCommandReceiver
import com.composetest.feature.root.ui.root.RootScreen
import com.composetest.feature.root.ui.root.RootUiEvent
import com.composetest.feature.root.ui.root.RootUiState
import com.composetest.feature.root.ui.root.RootViewModel

fun NavGraphBuilder.rootNavGraph() {
    composable2<RootDestination, RootViewModel, RootUiState, RootUiEvent, RootCommandReceiver>(
        screen = RootScreen,
        navigateBackHandler = false
    )
}