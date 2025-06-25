package com.composetest.feature.configuration.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.composetest.core.router.destination.configuration.ConfigurationDestination
import com.composetest.core.router.interfaces.NavGraph
import com.composetest.feature.configuration.presenter.ui.configuration.ConfigurationScreen
import com.composetest.feature.configuration.presenter.ui.configuration.ConfigurationViewModel

object RootConfigurationNavGraph : NavGraph {
    override fun NavGraphBuilder.register(
        navController: NavHostController
    ) {
        composable<ConfigurationDestination> {
            val viewModel = hiltViewModel<ConfigurationViewModel>()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            ConfigurationScreen(
                uiState = uiState,
                uiEvent = viewModel.uiEvent,
                navController = navController,
                onExecuteIntent = viewModel::executeIntent,
            )
        }
    }
}