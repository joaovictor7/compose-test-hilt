package com.composetest.feature.weatherforecast.presenter.ui

import com.composetest.core.ui.interfaces.IntentReceiver

internal interface WeatherForecastIntentReceiver : IntentReceiver<WeatherForecastIntentReceiver> {
    fun checkPermissionsResult()
    fun getLocationAndWeatherForecastsData()
    fun getWeatherForecastNowData()
    fun getWeatherForecastsData()
}