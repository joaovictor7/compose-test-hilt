package com.composetest.feature.weatherforecast.presenter.ui

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

    data object GetWeatherForecastNowData : WeatherForecastCommand {
        override fun execute(commandReceiver: WeatherForecastCommandReceiver) {
            commandReceiver.getWeatherForecastNowData()
        }
    }

    data object GetWeatherForecastsData : WeatherForecastCommand {
        override fun execute(commandReceiver: WeatherForecastCommandReceiver) {
            commandReceiver.getWeatherForecastsData()
        }
    }
}