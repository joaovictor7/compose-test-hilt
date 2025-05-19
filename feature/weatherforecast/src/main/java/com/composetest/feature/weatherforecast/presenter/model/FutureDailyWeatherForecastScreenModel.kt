package com.composetest.feature.weatherforecast.presenter.model

internal data class FutureDailyWeatherForecastScreenModel(
    val iconUrl: String,
    val temperature: String,
    val hour: String
)