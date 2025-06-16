package com.composetest.feature.weatherforecast.presenter.ui

import com.composetest.core.designsystem.enums.topbar.TopBarAction
import com.composetest.feature.weatherforecast.domain.model.TodayWeatherForecastModel
import com.composetest.feature.weatherforecast.presenter.enums.WeatherForecastScreenStatus
import com.composetest.feature.weatherforecast.presenter.enums.WeatherForecastStatus
import com.composetest.feature.weatherforecast.presenter.model.FutureWeatherForecastScreenModel
import com.composetest.feature.weatherforecast.presenter.model.TodayWeatherForecastScreenModel
import com.composetest.feature.weatherforecast.presenter.model.WeatherNowScreenModel

internal data class WeatherForecastUiState(
    val screenStatus: WeatherForecastScreenStatus = WeatherForecastScreenStatus.INITIAL,
    val weatherNow: WeatherNowScreenModel = WeatherNowScreenModel(),
    val todayWeatherForecast: TodayWeatherForecastScreenModel? = TodayWeatherForecastScreenModel(),
    val futureWeatherForecasts: List<FutureWeatherForecastScreenModel> = emptyList(),
    val weatherNowStatus: WeatherForecastStatus = WeatherForecastStatus.LOADING,
    val weatherForecastsStatus: WeatherForecastStatus = WeatherForecastStatus.LOADING,
) {

    val isLoading
        get() = WeatherForecastStatus.LOADING in listOf(
            weatherNowStatus,
            weatherForecastsStatus
        )

    val refreshButton
        get() = if (!showFullScreenMsg && !isLoading) listOf(TopBarAction.REFRESH) else null

    val showFullScreenMsg
        get() = !isLoading && screenStatus in listOf(
            WeatherForecastScreenStatus.TRY_AGAIN,
            WeatherForecastScreenStatus.PERMISSION_NOT_GRANTED,
            WeatherForecastScreenStatus.NEEDS_LOCATION,
        )

    val screenStatusIsPermissionNotGranted get() = screenStatus == WeatherForecastScreenStatus.PERMISSION_NOT_GRANTED

    val screenStatusIsTryAgain get() = screenStatus == WeatherForecastScreenStatus.TRY_AGAIN

    fun setScreenStatus(screenStatus: WeatherForecastScreenStatus) =
        copy(screenStatus = screenStatus)

    fun setTryAgainScreenError() = copy(
        screenStatus = WeatherForecastScreenStatus.TRY_AGAIN,
        weatherNowStatus = WeatherForecastStatus.ERROR,
        weatherForecastsStatus = WeatherForecastStatus.ERROR,
    )

    fun setScreenLoading() = copy(
        weatherNowStatus = WeatherForecastStatus.LOADING,
        weatherForecastsStatus = WeatherForecastStatus.LOADING
    )

    fun setWeatherNowStatus(status: WeatherForecastStatus) = copy(
        weatherNowStatus = status
    )

    fun setWeatherForecastsStatus(status: WeatherForecastStatus) = copy(
        weatherForecastsStatus = status
    )

    fun setWeatherNow(weatherNowModel: WeatherNowScreenModel) = copy(
        weatherNowStatus = WeatherForecastStatus.READY,
        weatherNow = weatherNowModel,
    )

    fun setWeatherForecasts(
        todayWeatherForecast: TodayWeatherForecastModel?,
        futureWeatherForecastScreens: List<FutureWeatherForecastScreenModel>
    ) = copy(
        weatherForecastsStatus = WeatherForecastStatus.READY,
        todayWeatherForecast = todayWeatherForecast?.let {
            TodayWeatherForecastScreenModel(
                minTemperature = todayWeatherForecast.minTemperature,
                maxTemperature = todayWeatherForecast.maxTemperature,
                temperatures = todayWeatherForecast.temperatures
            )
        },
        futureWeatherForecasts = futureWeatherForecastScreens
    )
}
