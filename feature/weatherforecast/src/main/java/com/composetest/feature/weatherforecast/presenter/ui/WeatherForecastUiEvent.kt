package com.composetest.feature.weatherforecast.presenter.ui

import com.composetest.core.router.model.NavigationModel

internal sealed interface WeatherForecastUiEvent {
    data object LaunchPermissionRequest : WeatherForecastUiEvent
    data class NavigateTo(val navigationModel: NavigationModel) : WeatherForecastUiEvent
}
