package com.composetest.feature.configuration.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import com.composetest.core.router.destinations.configuration.ConfigurationMenuDestination
import com.composetest.core.router.extensions.composable
import com.composetest.feature.configuration.ui.configuration.ConfigurationMenuScreen
import com.composetest.feature.configuration.ui.configuration.ConfigurationMenuViewModel

fun NavGraphBuilder.configurationNavGraph() {
    composable<ConfigurationMenuDestination> {
        val viewModel = hiltViewModel<ConfigurationMenuViewModel>()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        ConfigurationMenuScreen(uiState = uiState, onExecuteCommand = viewModel::executeCommand)
    }
}