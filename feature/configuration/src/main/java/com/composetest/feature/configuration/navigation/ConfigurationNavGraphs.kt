package com.composetest.feature.configuration.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.composetest.core.router.interfaces.NavGraph
import com.composetest.feature.configuration.navigation.destinaition.SecurityConfigurationDestination
import com.composetest.feature.configuration.navigation.destinaition.ThemeConfigurationDestination
import com.composetest.feature.configuration.presenter.ui.security.SecurityConfigurationScreen
import com.composetest.feature.configuration.presenter.ui.security.SecurityConfigurationViewModel
import com.composetest.feature.configuration.presenter.ui.theme.ThemeConfigurationScreen
import com.composetest.feature.configuration.presenter.ui.theme.ThemeConfigurationViewModel

object ConfigurationNavGraph : NavGraph {
    override fun NavGraphBuilder.register(
        navController: NavHostController
    ) {
        composable<ThemeConfigurationDestination> {
            val viewModel = hiltViewModel<ThemeConfigurationViewModel>()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            ThemeConfigurationScreen(
                uiState = uiState,
                onExecuteIntent = viewModel::executeIntent,
            )
        }
        composable<SecurityConfigurationDestination> {
            val viewModel = hiltViewModel<SecurityConfigurationViewModel>()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            SecurityConfigurationScreen(
                uiState = uiState,
                onExecuteIntent = viewModel::executeIntent,
            )
        }
    }
}