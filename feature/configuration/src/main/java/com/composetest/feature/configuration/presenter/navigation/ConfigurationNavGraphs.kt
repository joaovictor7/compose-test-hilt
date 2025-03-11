package com.composetest.feature.configuration.presenter.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.composetest.core.router.destinations.configuration.SecurityConfigurationDestination
import com.composetest.core.router.destinations.configuration.ThemeConfigurationDestination
import com.composetest.feature.configuration.presenter.ui.security.SecurityConfigurationScreen
import com.composetest.feature.configuration.presenter.ui.security.SecurityConfigurationViewModel
import com.composetest.feature.configuration.presenter.ui.theme.ThemeConfigurationScreen
import com.composetest.feature.configuration.presenter.ui.theme.ThemeConfigurationViewModel

fun NavGraphBuilder.configurationNavGraphs() {
    composable<ThemeConfigurationDestination> {
        val viewModel = hiltViewModel<ThemeConfigurationViewModel>()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        ThemeConfigurationScreen(
            uiState = uiState,
            onExecuteCommand = viewModel::executeCommand,
        )
    }
    composable<SecurityConfigurationDestination> {
        val viewModel = hiltViewModel<SecurityConfigurationViewModel>()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        SecurityConfigurationScreen(
            uiState = uiState,
            onExecuteCommand = viewModel::executeCommand,
        )
    }
}