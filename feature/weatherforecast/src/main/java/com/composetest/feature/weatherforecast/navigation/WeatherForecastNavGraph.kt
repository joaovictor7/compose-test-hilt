package com.composetest.feature.weatherforecast.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.composetest.core.router.destination.weatherforecast.WeatherForecastDestination
import com.composetest.core.router.interfaces.NavGraph
import com.composetest.core.ui.util.transformDeepLinks
import com.composetest.feature.weatherforecast.presenter.ui.WeatherForecastScreen
import com.composetest.feature.weatherforecast.presenter.ui.WeatherForecastViewModel

private const val WEATHER_FORECAST_URI = "composetest://weatherforecast"

object WeatherForecastNavGraph : NavGraph {
    override fun NavGraphBuilder.register(
        navController: NavHostController
    ) {
        composable<WeatherForecastDestination>(
            deepLinks = transformDeepLinks(WEATHER_FORECAST_URI)
        ) {
            val viewModel = hiltViewModel<WeatherForecastViewModel>()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            WeatherForecastScreen(
                uiState = uiState,
                uiEvent = viewModel.uiEvent,
                onExecuteIntent = viewModel::executeIntent,
                navController = navController,
            )
        }
    }
}