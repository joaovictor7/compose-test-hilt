package com.composetest.feature.weatherforecast.ui.weatherforecast

import com.composetest.core.ui.interfaces.Command

internal sealed interface WeatherForecastCommand : Command<WeatherForecastCommandReceiver> {

}