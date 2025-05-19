package com.composetest.feature.root.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.composetest.core.router.destination.root.RootDestination
import com.composetest.feature.root.presentation.ui.root.RootScreen
import com.composetest.feature.root.presentation.ui.root.RootViewModel

fun NavGraphBuilder.rootNavGraphs(mainNavController: NavHostController) {
    composable<RootDestination> {
        val viewModel = hiltViewModel<RootViewModel>()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        RootScreen(
            uiState = uiState,
            uiEvent = viewModel.uiEvent,
            onExecuteCommand = viewModel::executeCommand,
            mainNavController = mainNavController
        )
    }
}