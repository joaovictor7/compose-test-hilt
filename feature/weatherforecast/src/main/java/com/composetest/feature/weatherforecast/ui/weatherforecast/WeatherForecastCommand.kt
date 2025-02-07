package com.composetest.feature.weatherforecast.ui.weatherforecast

import com.composetest.core.ui.interfaces.Command

internal sealed interface WeatherForecastCommand : Command<WeatherForecastCommandReceiver> {

    data object CheckPermissionsResult : WeatherForecastCommand {
        override fun execute(commandReceiver: WeatherForecastCommandReceiver) {
            commandReceiver.checkPermissionsResult()
        }
    }

    data object GetLocationAndWeatherForecastsData : WeatherForecastCommand {
        override fun execute(commandReceiver: WeatherForecastCommandReceiver) {
            commandReceiver.getLocationAndWeatherForecastsData()
        }
    }

    data object DismissSimpleDialog : WeatherForecastCommand {
        override fun execute(commandReceiver: WeatherForecastCommandReceiver) {
            commandReceiver.dismissSimpleDialog()
        }
    }
}