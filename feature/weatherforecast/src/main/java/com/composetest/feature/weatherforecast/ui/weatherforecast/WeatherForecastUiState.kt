package com.composetest.feature.weatherforecast.ui.weatherforecast

import com.composetest.core.ui.interfaces.BaseUiState

internal data class WeatherForecastUiState(
    val t: String = String(),
) : BaseUiState
