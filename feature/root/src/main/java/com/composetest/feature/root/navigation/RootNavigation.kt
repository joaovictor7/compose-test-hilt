package com.composetest.feature.root.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import com.composetest.core.router.destinations.home.HomeDestination
import com.composetest.core.router.destinations.root.RootDestination
import com.composetest.core.router.extensions.composable
import com.composetest.feature.root.ui.RootScreen
import com.composetest.feature.root.ui.RootViewModel

fun NavGraphBuilder.rootNavGraph() {
    composable<RootDestination> {
        val viewModel = hiltViewModel<RootViewModel>()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        RootScreen(uiState = uiState, onExecuteCommand = viewModel::executeCommand)
    }
}