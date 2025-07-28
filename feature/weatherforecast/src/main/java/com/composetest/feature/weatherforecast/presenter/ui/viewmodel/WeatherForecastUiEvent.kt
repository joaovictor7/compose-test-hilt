package com.composetest.feature.weatherforecast.presenter.ui.viewmodel

import com.composetest.core.router.model.NavigationModel

internal sealed interface WeatherForecastUiEvent {
    data object LaunchPermissionRequest : WeatherForecastUiEvent
    data class NavigateTo(val navigationModel: NavigationModel) : WeatherForecastUiEvent
}
