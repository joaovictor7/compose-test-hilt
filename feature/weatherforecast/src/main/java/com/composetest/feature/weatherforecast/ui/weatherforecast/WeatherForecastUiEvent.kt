package com.composetest.feature.weatherforecast.ui.weatherforecast

internal sealed interface WeatherForecastUiEvent {
    data object LaunchPermissionRequest : WeatherForecastUiEvent
}
