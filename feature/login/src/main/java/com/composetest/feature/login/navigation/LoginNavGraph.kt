package com.composetest.feature.login.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.composetest.core.router.destinations.login.LoginDestination
import com.composetest.feature.login.presenter.ui.login.LoginScreen
import com.composetest.feature.login.presenter.ui.login.LoginViewModel

fun NavGraphBuilder.loginNavGraphs(naController: NavHostController) {
    composable<LoginDestination> {
        val viewModel = hiltViewModel<LoginViewModel>()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        LoginScreen(
            uiState = uiState,
            uiEvent = viewModel.uiEvent,
            onExecuteCommand = viewModel::executeCommand,
            navController = naController
        )
    }
}