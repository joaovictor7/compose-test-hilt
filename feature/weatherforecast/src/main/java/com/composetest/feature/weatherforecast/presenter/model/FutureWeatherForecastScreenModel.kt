package com.composetest.feature.weatherforecast.presenter.model

internal data class FutureWeatherForecastScreenModel(
    val day: String,
    val futureDailyWeatherForecasts: List<FutureDailyWeatherForecastScreenModel>
)