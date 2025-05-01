package com.composetest.feature.weatherforecast.presenter.ui

import com.composetest.core.ui.interfaces.CommandReceiver

internal interface WeatherForecastCommandReceiver :
    CommandReceiver<WeatherForecastCommandReceiver> {

    fun checkPermissionsResult()
    fun getLocationAndWeatherForecastsData()
    fun getWeatherForecastNowData()
    fun getWeatherForecastsData()
}