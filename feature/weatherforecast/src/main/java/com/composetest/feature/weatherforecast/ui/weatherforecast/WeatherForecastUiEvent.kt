package com.composetest.feature.weatherforecast.ui.weatherforecast

import com.composetest.core.router.models.NavigationModel

internal sealed interface WeatherForecastUiEvent {
    data object LaunchPermissionRequest : WeatherForecastUiEvent
    data class NavigateTo(val navigationModel: NavigationModel) : WeatherForecastUiEvent
}
