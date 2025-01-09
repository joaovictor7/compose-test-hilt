package com.composetest.feature.weatherforecast.mappers

import com.composetest.common.providers.LocaleProvider
import com.composetest.core.domain.models.weatherforecast.WeatherNowModel
import com.composetest.feature.weatherforecast.models.WeatherNowScreenModel
import javax.inject.Inject

internal class WeatherNowScreenModelMapper @Inject constructor(
    private val localeProvider: LocaleProvider
) {

    operator fun invoke(weatherNowModel: WeatherNowModel) = WeatherNowScreenModel(
        city = weatherNowModel.city,
        temperature = "${weatherNowModel.temperature.toInt()}º",
        iconId = weatherNowModel.iconId,
        description = weatherNowModel.description.replaceFirstChar {
            it.titlecase(localeProvider.default)
        }
    )
}