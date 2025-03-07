package com.composetest.feature.weatherforecast.data.mappers

import com.composetest.common.extensions.convertedFromUnix
import com.composetest.feature.weatherforecast.domain.models.ForecastTemperatureModel
import com.composetest.feature.weatherforecast.domain.models.WeatherForecastLocationModel
import com.composetest.feature.weatherforecast.domain.models.WeatherForecastModel
import com.composetest.feature.weatherforecast.domain.models.WeatherNowModel
import com.composetest.feature.weatherforecast.network.requests.WeatherForecastRequest
import com.composetest.feature.weatherforecast.network.responses.WeatherForecastResponse
import com.composetest.feature.weatherforecast.network.responses.WeatherNowResponse
import javax.inject.Inject

internal class WeatherForecastMapper @Inject constructor() {

    fun mapperToModel(response: WeatherNowResponse): WeatherNowModel {
        val lastWeatherNowData = response.weatherDataList.last()
        return WeatherNowModel(
            city = response.city,
            temperature = response.temperatureData.temperature,
            iconId = lastWeatherNowData.icon,
            description = lastWeatherNowData.description
        )
    }

    fun mapperToModels(response: WeatherForecastResponse): List<WeatherForecastModel> {
        return response.dataList.map {
            WeatherForecastModel(
                dateTime = it.dateTime.convertedFromUnix,
                forecastTemperature = ForecastTemperatureModel(
                    iconId = it.weatherData.first().icon,
                    temperature = it.temperatureData.temperature,
                    minTemperature = it.temperatureData.minTemperature,
                    maxTemperature = it.temperatureData.maxTemperature
                )
            )
        }
    }

    fun mapperToRequest(model: WeatherForecastLocationModel) = WeatherForecastRequest(
        latitude = model.latitude.toString(),
        longitude = model.longitude.toString(),
        language = model.language,
        metric = model.metric
    )
}