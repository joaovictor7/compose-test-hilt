package com.composetest.feature.weatherforecast.presenter.ui.viewmodel

import com.composetest.core.designsystem.component.topbar.enums.TopBarAction
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
        get() = screenStatus in listOf(
            WeatherForecastScreenStatus.NEEDS_LOCATION,
            WeatherForecastScreenStatus.PERMISSION_NOT_GRANTED,
        ) || (!isLoading && screenStatus == WeatherForecastScreenStatus.TRY_AGAIN)

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
