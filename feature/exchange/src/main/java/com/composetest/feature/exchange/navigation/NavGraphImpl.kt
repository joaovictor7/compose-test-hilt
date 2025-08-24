package com.composetest.feature.exchange.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.composetest.core.router.destination.exchange.ExchangeListDestination
import com.composetest.core.router.interfaces.NavGraph
import com.composetest.core.ui.util.provideDeepLinks
import com.composetest.feature.exchange.navigation.destination.ExchangeDetailDestination
import com.composetest.feature.exchange.presenter.ui.detail.ExchangeDetailScreen
import com.composetest.feature.exchange.presenter.ui.detail.viewmodel.ExchangeDetailViewModel
import com.composetest.feature.exchange.presenter.ui.list.ExchangeListScreen
import com.composetest.feature.exchange.presenter.ui.list.viewmodel.ExchangeListViewModel
import javax.inject.Inject

private const val EXCHANGES_URI = "composetest://exchange/{filter}"

internal class NavGraphImpl @Inject constructor() : NavGraph {
    override fun NavGraphBuilder.register(navController: NavHostController) {
        composable<ExchangeListDestination>(
            deepLinks = provideDeepLinks<ExchangeListDestination>(EXCHANGES_URI)
        ) {
            val viewModel: ExchangeListViewModel = hiltViewModel()
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
}