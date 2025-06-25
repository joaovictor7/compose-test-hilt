package com.composetest.feature.weatherforecast.presenter.enums

import androidx.annotation.StringRes
import com.composetest.feature.weatherforecast.R

internal enum class WeatherForecastScreenStatus(@param:StringRes val titleId: Int? = null) {
    INITIAL,
    READY,
    PERMISSION_NOT_GRANTED(R.string.weather_forecast_required_permission_msg),
    TRY_AGAIN,
    NEEDS_LOCATION(R.string.weather_forecast_enable_location_msg),
}