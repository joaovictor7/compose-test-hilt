package com.composetest.feature.weatherforecast.presenter.ui

import com.composetest.core.ui.interfaces.Intent

internal sealed interface WeatherForecastIntent : Intent<WeatherForecastIntentReceiver> {

    data object CheckPermissionsResult : WeatherForecastIntent {
        override fun execute(intentReceiver: WeatherForecastIntentReceiver) {
            intentReceiver.checkPermissionsResult()
        }
    }

    data object GetLocationAndWeatherForecastsData : WeatherForecastIntent {
        override fun execute(intentReceiver: WeatherForecastIntentReceiver) {
            intentReceiver.getLocationAndWeatherForecastsData()
        }
    }

    data object GetWeatherForecastNowData : WeatherForecastIntent {
        override fun execute(intentReceiver: WeatherForecastIntentReceiver) {
            intentReceiver.getWeatherForecastNowData()
        }
    }

    data object GetWeatherForecastsData : WeatherForecastIntent {
        override fun execute(intentReceiver: WeatherForecastIntentReceiver) {
            intentReceiver.getWeatherForecastsData()
        }
    }
}