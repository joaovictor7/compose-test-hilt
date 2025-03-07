package com.composetest.feature.weatherforecast.presenter.models

internal data class FutureWeatherForecastScreenModel(
    val day: String,
    val futureDailyWeatherForecasts: List<FutureDailyWeatherForecastScreenModel>
)