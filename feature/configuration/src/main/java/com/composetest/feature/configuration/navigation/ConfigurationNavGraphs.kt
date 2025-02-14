package com.composetest.feature.configuration.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.composetest.core.router.destinations.configuration.ConfigurationSecurityDestination
import com.composetest.core.router.destinations.configuration.ConfigurationThemeDestination
import com.composetest.feature.configuration.ui.security.ConfigurationSecurityScreen
import com.composetest.feature.configuration.ui.security.ConfigurationSecurityViewModel
import com.composetest.feature.configuration.ui.theme.ConfigurationThemeScreen
import com.composetest.feature.configuration.ui.theme.ConfigurationThemeViewModel

fun NavGraphBuilder.configurationNavGraphs() {
    composable<ConfigurationThemeDestination> {
        val viewModel = hiltViewModel<ConfigurationThemeViewModel>()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        ConfigurationThemeScreen(
            uiState = uiState,
            onExecuteCommand = viewModel::executeCommand,
        )
    }
    composable<ConfigurationSecurityDestination> {
        val viewModel = hiltViewModel<ConfigurationSecurityViewModel>()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        ConfigurationSecurityScreen(
            uiState = uiState,
            onExecuteCommand = viewModel::executeCommand,
        )
    }
}