package com.composetest.feature.configuration.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.composetest.core.router.destinations.configuration.ConfigurationDestination
import com.composetest.core.router.destinations.configuration.ConfigurationRootDestination
import com.composetest.core.router.extensions.composable
import com.composetest.feature.configuration.ui.configuration.ConfigurationScreen
import com.composetest.feature.configuration.ui.configuration.ConfigurationViewModel

fun NavGraphBuilder.configurationNavGraph() {
    navigation<ConfigurationRootDestination>(startDestination = ConfigurationDestination) {
        composable<ConfigurationDestination> {
            val viewModel = hiltViewModel<ConfigurationViewModel>()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            ConfigurationScreen(uiState = uiState, onExecuteCommand = viewModel::executeCommand)
        }
    }
}