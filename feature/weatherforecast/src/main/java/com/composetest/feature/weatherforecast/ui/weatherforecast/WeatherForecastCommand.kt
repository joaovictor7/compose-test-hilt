package com.composetest.feature.weatherforecast.ui.weatherforecast

import com.composetest.core.ui.interfaces.Command

internal sealed interface WeatherForecastCommand : Command<WeatherForecastCommandReceiver> {

    data object Refresh : WeatherForecastCommand {
        override fun execute(commandReceiver: WeatherForecastCommandReceiver) {
            commandReceiver.refresh()
        }
    }

    data object DismissSimpleDialog : WeatherForecastCommand {
        override fun execute(commandReceiver: WeatherForecastCommandReceiver) {
            commandReceiver.dismissSimpleDialog()
        }
    }
}