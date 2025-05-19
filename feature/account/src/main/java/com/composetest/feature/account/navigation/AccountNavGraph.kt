package com.composetest.feature.account.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.composetest.core.router.destinations.profile.ProfileDestination
import com.composetest.feature.account.presentation.ui.account.AccountScreen
import com.composetest.feature.account.presentation.ui.account.AccountViewModel

fun NavGraphBuilder.accountNavGraphs(navController: NavHostController) {
    composable<ProfileDestination> {
        val viewModel = hiltViewModel<AccountViewModel>()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        AccountScreen(
            uiState = uiState,
            uiEvent = viewModel.uiEvent,
            onExecuteCommand = viewModel::executeCommand,
            navController = navController
        )
    }
}