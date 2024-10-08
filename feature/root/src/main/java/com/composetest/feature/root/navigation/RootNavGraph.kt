package com.composetest.feature.root.navigation

import androidx.navigation.NavGraphBuilder
import com.composetest.core.router.destinations.root.RootDestination
import com.composetest.core.router.extensions.composable
import com.composetest.feature.root.ui.root.RootCommandReceiver
import com.composetest.feature.root.ui.root.RootScreen
import com.composetest.feature.root.ui.root.RootUiState
import com.composetest.feature.root.ui.root.RootViewModel

fun NavGraphBuilder.rootNavGraph() {
    composable<RootDestination, RootViewModel, RootUiState, RootCommandReceiver>(RootScreen)
}