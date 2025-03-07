package com.composetest.feature.weatherforecast.presenter.models

internal data class TodayWeatherForecastScreenModel(
    val minTemperature: Float = 0f,
    val maxTemperature: Float = 0f,
    val temperatures: List<Float> = emptyList()
)