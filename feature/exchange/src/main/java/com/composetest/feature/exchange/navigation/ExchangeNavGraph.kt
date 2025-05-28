package com.composetest.feature.exchange.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.composetest.core.router.destination.exchange.ExchangeDetailDestination
import com.composetest.core.router.destination.exchange.ExchangeListDestination
import com.composetest.feature.exchange.presenter.ui.detail.ExchangeDetailScreen
import com.composetest.feature.exchange.presenter.ui.detail.ExchangeDetailViewModel
import com.composetest.feature.exchange.presenter.ui.list.ExchangeListScreen
import com.composetest.feature.exchange.presenter.ui.list.ExchangeListViewModel

fun NavGraphBuilder.exchangeNavGraphs(navController: NavHostController) {
    composable<ExchangeListDestination> {
        val viewModel = hiltViewModel<ExchangeListViewModel>()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        ExchangeListScreen(
            uiState = uiState,
            uiEvent = viewModel.uiEvent,
            onExecuteIntent = viewModel::executeIntent,
            navController = navController
        )
    }
    composable<ExchangeDetailDestination> {
        val viewModel = hiltViewModel<ExchangeDetailViewModel>()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        ExchangeDetailScreen(uiState = uiState)
    }
}