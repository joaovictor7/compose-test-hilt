package com.composetest.feature.configuration.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import com.composetest.core.router.destinations.configuration.ConfigurationsMenuDestination
import com.composetest.core.router.extensions.composable
import com.composetest.feature.configuration.ui.menu.ConfigurationsMenuScreen
import com.composetest.feature.configuration.ui.menu.ConfigurationsMenuViewModel

fun NavGraphBuilder.configurationNavGraph() {
    composable<ConfigurationsMenuDestination> {
        val viewModel = hiltViewModel<ConfigurationsMenuViewModel>()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        ConfigurationsMenuScreen(uiState = uiState, onExecuteCommand = viewModel::executeCommand)
    }
}