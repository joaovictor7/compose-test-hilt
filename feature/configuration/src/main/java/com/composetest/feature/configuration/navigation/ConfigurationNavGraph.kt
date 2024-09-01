package com.composetest.feature.configuration.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import com.composetest.core.router.destinations.configuration.ConfigurationDestination
import com.composetest.core.router.destinations.configuration.ConfigurationThemeDestination
import com.composetest.core.router.extensions.composable
import com.composetest.feature.configuration.ui.configuration.ConfigurationScreen
import com.composetest.feature.configuration.ui.configuration.ConfigurationViewModel
import com.composetest.feature.configuration.ui.theme.ConfigurationThemeScreen
import com.composetest.feature.configuration.ui.theme.ConfigurationThemeViewModel

fun NavGraphBuilder.configurationRootNavGraph() {
    composable<ConfigurationDestination> {
        val viewModel = hiltViewModel<ConfigurationViewModel>()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        ConfigurationScreen(uiState = uiState, onExecuteCommand = viewModel::executeCommand)
    }
}

fun NavGraphBuilder.configurationNavGraph() {
    composable<ConfigurationThemeDestination> {
        val viewModel = hiltViewModel<ConfigurationThemeViewModel>()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        ConfigurationThemeScreen(uiState = uiState, onExecuteCommand = viewModel::executeCommand)
    }
}