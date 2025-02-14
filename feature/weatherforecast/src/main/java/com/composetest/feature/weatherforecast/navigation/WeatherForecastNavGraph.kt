package com.composetest.feature.weatherforecast.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.composetest.core.router.destinations.weatherforecast.WeatherForecastDestination
import com.composetest.feature.weatherforecast.ui.weatherforecast.WeatherForecastScreen
import com.composetest.feature.weatherforecast.ui.weatherforecast.WeatherForecastViewModel

fun NavGraphBuilder.weatherForecastNavGraphs() {
    composable<WeatherForecastDestination> {
        val viewModel = hiltViewModel<WeatherForecastViewModel>()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        WeatherForecastScreen(
            uiState = uiState,
            uiEvent = viewModel.uiEvent,
            onExecuteCommand = viewModel::executeCommand,
        )
    }
}