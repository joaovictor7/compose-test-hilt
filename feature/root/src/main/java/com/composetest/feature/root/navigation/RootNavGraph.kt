package com.composetest.feature.root.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.composetest.core.router.destination.root.RootDestination
import com.composetest.core.router.interfaces.NavGraph
import com.composetest.feature.root.presentation.ui.root.RootScreen
import com.composetest.feature.root.presentation.ui.root.RootViewModel

object RootNavGraph : NavGraph {
    override fun NavGraphBuilder.register(
        navController: NavHostController
    ) {
        composable<RootDestination> {
            val viewModel = hiltViewModel<RootViewModel>()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            RootScreen(
                uiState = uiState,
                uiEvent = viewModel.uiEvent,
                onExecuteIntent = viewModel::executeIntent,
                navController = navController
            )
        }
    }
}