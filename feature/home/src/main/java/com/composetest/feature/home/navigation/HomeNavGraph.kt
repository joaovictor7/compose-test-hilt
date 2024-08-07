package com.composetest.feature.home.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import com.composetest.core.router.destinations.home.Home2Destination
import com.composetest.core.router.destinations.home.Home3Destination
import com.composetest.core.router.destinations.home.HomeDestination
import com.composetest.core.router.extensions.composable
import com.composetest.feature.home.ui.home.HomeScreen
import com.composetest.feature.home.ui.home.HomeViewModel
import com.composetest.feature.home.ui.home2.Home2Screen
import com.composetest.feature.home.ui.home2.Home2ViewModel
import com.composetest.feature.home.ui.home3.Home3Screen
import com.composetest.feature.home.ui.home3.Home3ViewModel

fun NavGraphBuilder.homeNavGraph() {
    composable<HomeDestination> {
        val viewModel = hiltViewModel<HomeViewModel>()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        HomeScreen(uiState = uiState, onExecuteCommand = viewModel::executeCommand)
    }
    composable<Home2Destination> {
        val viewModel = hiltViewModel<Home2ViewModel>()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        Home2Screen(uiState = uiState, onExecuteCommand = viewModel::executeCommand)
    }
    composable<Home3Destination> {
        val viewModel = hiltViewModel<Home3ViewModel>()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        Home3Screen(uiState = uiState, onExecuteCommand = viewModel::executeCommand)
    }
}