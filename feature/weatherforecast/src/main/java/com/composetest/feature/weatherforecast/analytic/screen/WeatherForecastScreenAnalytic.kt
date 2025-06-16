package com.composetest.feature.weatherforecast.analytic.screen

import com.composetest.feature.weatherforecast.analytic.WeatherForecastModuleAnalytic

internal object WeatherForecastScreenAnalytic : WeatherForecastModuleAnalytic() {
    const val SCREEN = "weather_forecast"
    override val screen = SCREEN
}