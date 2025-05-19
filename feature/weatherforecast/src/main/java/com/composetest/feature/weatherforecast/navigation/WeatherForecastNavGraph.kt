package com.composetest.feature.weatherforecast.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.composetest.core.router.destination.weatherforecast.WeatherForecastDestination
import com.composetest.feature.weatherforecast.presenter.ui.WeatherForecastScreen
import com.composetest.feature.weatherforecast.presenter.ui.WeatherForecastViewModel

fun NavGraphBuilder.weatherForecastNavGraphs(navController: NavHostController) {
    composable<WeatherForecastDestination> {
        val viewModel = hiltViewModel<WeatherForecastViewModel>()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        WeatherForecastScreen(
            uiState = uiState,
            uiEvent = viewModel.uiEvent,
            onExecuteCommand = viewModel::executeCommand,
            navController = navController,
        )
    }
}