package com.composetest.feature.weatherforecast.presenter.ui

internal sealed interface WeatherForecastUiEvent {
    data object LaunchPermissionRequest : WeatherForecastUiEvent
}
