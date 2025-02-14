package com.composetest.feature.configuration.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.composetest.core.router.destinations.configuration.ConfigurationDestination
import com.composetest.feature.configuration.ui.configuration.ConfigurationScreen
import com.composetest.feature.configuration.ui.configuration.ConfigurationViewModel

fun NavGraphBuilder.rootConfigurationNavGraphs(mainNavController: NavHostController) {
    composable<ConfigurationDestination> {
        val viewModel = hiltViewModel<ConfigurationViewModel>()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        ConfigurationScreen(
            uiState = uiState,
            uiEvent = viewModel.uiEvent,
            mainNavController = mainNavController,
            onExecuteCommand = viewModel::executeCommand,
        )
    }
}