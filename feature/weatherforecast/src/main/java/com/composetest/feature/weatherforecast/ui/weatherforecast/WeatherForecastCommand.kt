package com.composetest.feature.weatherforecast.ui.weatherforecast

import com.composetest.core.ui.interfaces.Command

internal sealed interface WeatherForecastCommand : Command<WeatherForecastCommandReceiver> {

    data class CheckPermissionsResult(
        private val permissions: Map<String, Boolean>
    ) : WeatherForecastCommand {
        override fun execute(commandReceiver: WeatherForecastCommandReceiver) {
            commandReceiver.checkPermissionsResult(permissions)
        }
    }

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