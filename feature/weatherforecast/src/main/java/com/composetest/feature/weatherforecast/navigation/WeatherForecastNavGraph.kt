package com.composetest.feature.weatherforecast.navigation

import androidx.navigation.NavGraphBuilder
import com.composetest.core.router.destinations.weatherforecast.WeatherForecastDestination
import com.composetest.core.ui.extensions.buildComposable
import com.composetest.feature.weatherforecast.ui.weatherforecast.WeatherForecastCommandReceiver
import com.composetest.feature.weatherforecast.ui.weatherforecast.WeatherForecastScreen
import com.composetest.feature.weatherforecast.ui.weatherforecast.WeatherForecastUiEvent
import com.composetest.feature.weatherforecast.ui.weatherforecast.WeatherForecastUiState
import com.composetest.feature.weatherforecast.ui.weatherforecast.WeatherForecastViewModel

fun NavGraphBuilder.weatherForecastNavGraph() {
    buildComposable<WeatherForecastDestination, WeatherForecastViewModel, WeatherForecastUiState, WeatherForecastUiEvent, WeatherForecastCommandReceiver>(
        screen = WeatherForecastScreen
    )
}