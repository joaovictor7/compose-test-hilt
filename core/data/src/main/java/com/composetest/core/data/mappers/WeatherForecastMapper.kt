package com.composetest.core.data.mappers

import com.composetest.common.extensions.unixToLocalDateTime
import com.composetest.core.data.api.responses.weatherforecast.WeatherForecastResponse
import com.composetest.core.data.api.responses.weatherforecast.WeatherNowResponse
import com.composetest.core.domain.models.weatherforecast.ForecastTemperatureModel
import com.composetest.core.domain.models.weatherforecast.WeatherForecastModel
import com.composetest.core.domain.models.weatherforecast.WeatherNowModel
import javax.inject.Inject

internal class WeatherForecastMapper @Inject constructor() {

    operator fun invoke(response: WeatherNowResponse): WeatherNowModel {
        val lastWeatherNowData = response.weatherDataList.last()
        return WeatherNowModel(
            city = response.city,
            temperature = response.temperatureData.temperature,
            iconId = lastWeatherNowData.icon,
            description = lastWeatherNowData.description
        )
    }

    operator fun invoke(response: WeatherForecastResponse): List<WeatherForecastModel> {
        return response.dataList.map {
            WeatherForecastModel(
                dateTime = it.dateTime.unixToLocalDateTime,
                forecastTemperature = ForecastTemperatureModel(
                    temperature = it.temperatureData.temperature,
                    minTemperature = it.temperatureData.minTemperature,
                    maxTemperature = it.temperatureData.maxTemperature
                )
            )
        }
    }
}