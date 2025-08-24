package com.composetest.feature.findroute.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.composetest.core.router.destination.findroute.FindRouteDestination
import com.composetest.core.router.interfaces.NavGraph
import com.composetest.core.ui.util.provideDeepLinks
import com.composetest.feature.findroute.presenter.ui.findroute.NewsListScreen
import com.composetest.feature.findroute.presenter.ui.findroute.viewmodel.FindRouteViewModel
import javax.inject.Inject

private const val FIND_ROUTE_URI = "composetest://findroute"

internal class NavGraphImpl @Inject constructor() : NavGraph {
    override fun NavGraphBuilder.register(navController: NavHostController) {
        composable<FindRouteDestination>(
            deepLinks = provideDeepLinks<FindRouteDestination>(FIND_ROUTE_URI)
        ) {
            val viewModel = hiltViewModel<FindRouteViewModel>()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            NewsListScreen(
                uiState = uiState,
                uiEvent = viewModel.uiEvent,
                onExecuteIntent = viewModel::executeIntent,
                navController = navController
            )
        }
    }
}