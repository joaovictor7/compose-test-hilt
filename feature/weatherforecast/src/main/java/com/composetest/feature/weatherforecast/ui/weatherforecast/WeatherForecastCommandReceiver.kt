package com.composetest.feature.weatherforecast.ui.weatherforecast

import com.composetest.core.ui.interfaces.CommandReceiver

internal interface WeatherForecastCommandReceiver :
    CommandReceiver<WeatherForecastCommandReceiver> {

    fun checkPermissionsResult(permissions: Map<String, Boolean>)
    fun getWeatherForecastData()
    fun dismissSimpleDialog()
}