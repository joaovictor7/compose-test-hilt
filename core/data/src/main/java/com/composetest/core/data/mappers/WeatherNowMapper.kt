package com.composetest.core.data.mappers

import com.composetest.core.network.responses.weatherforecast.WeatherNowDataResponse
import com.composetest.core.network.responses.weatherforecast.WeatherNowResponse
import com.composetest.core.domain.models.weatherforecast.WeatherNowModel
import javax.inject.Inject

internal class WeatherNowMapper @Inject constructor() {

    operator fun invoke(
        response: WeatherNowResponse,
        weatherNowDataResponse: WeatherNowDataResponse
    ) = WeatherNowModel(
        city = response.city,
        temperature = response.temperatureData.temperature,
        iconId = weatherNowDataResponse.icon,
        description = weatherNowDataResponse.description
    )
}