package com.composetest.feature.weatherforecast.presenter.ui.viewmodel

import com.composetest.core.ui.interfaces.IntentReceiver

internal interface WeatherForecastIntentReceiver : IntentReceiver<WeatherForecastIntentReceiver> {
    fun onResume()
    fun getLocationAndWeatherForecastsData()
    fun getWeatherForecastNowData()
    fun getWeatherForecastsData()
}