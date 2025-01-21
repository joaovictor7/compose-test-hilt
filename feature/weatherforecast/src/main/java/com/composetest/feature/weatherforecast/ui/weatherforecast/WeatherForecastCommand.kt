package com.composetest.feature.weatherforecast.ui.weatherforecast

import com.composetest.core.ui.interfaces.Command

internal sealed interface WeatherForecastCommand : Command<WeatherForecastCommandReceiver> {

    data object GetWeatherForecastData : WeatherForecastCommand {
        override fun execute(commandReceiver: WeatherForecastCommandReceiver) {
            commandReceiver.getWeatherForecastData()
        }
    }

    data object DismissSimpleDialog : WeatherForecastCommand {
        override fun execute(commandReceiver: WeatherForecastCommandReceiver) {
            commandReceiver.dismissSimpleDialog()
        }
    }
}