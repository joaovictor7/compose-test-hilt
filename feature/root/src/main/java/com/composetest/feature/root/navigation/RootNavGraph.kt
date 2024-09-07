package com.composetest.feature.root.navigation

import androidx.navigation.NavGraphBuilder
import com.composetest.core.router.destinations.root.RootDestination
import com.composetest.core.router.extensions.composable
import com.composetest.feature.root.ui.RootCommandReceiver
import com.composetest.feature.root.ui.RootScreen
import com.composetest.feature.root.ui.RootUiState
import com.composetest.feature.root.ui.RootViewModel

fun NavGraphBuilder.rootNavGraph() {
    composable<RootDestination, RootViewModel, RootUiState, RootCommandReceiver>(RootScreen)
}