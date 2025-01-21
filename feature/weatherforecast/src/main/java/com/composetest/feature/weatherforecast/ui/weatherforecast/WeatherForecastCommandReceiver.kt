package com.composetest.feature.weatherforecast.ui.weatherforecast

import com.composetest.core.ui.interfaces.CommandReceiver

internal interface WeatherForecastCommandReceiver :
    CommandReceiver<WeatherForecastCommandReceiver> {

    fun getWeatherForecastData()
    fun dismissSimpleDialog()
}