package com.composetest.feature.weatherforecast.ui.weatherforecast

import android.location.Location
import com.composetest.common.providers.LocationProvider
import com.composetest.core.domain.usecases.GeTodayWeatherForecastUseCase
import com.composetest.core.domain.usecases.GetFutureWeatherForecastUseCase
import com.composetest.core.domain.usecases.GetWeatherForecastsUseCase
import com.composetest.core.domain.usecases.GetWeatherNowUseCase
import com.composetest.core.domain.usecases.SendAnalyticsUseCase
import com.composetest.core.router.di.qualifiers.NavGraphQualifier
import com.composetest.core.router.enums.NavGraph
import com.composetest.core.router.managers.NavigationManager
import com.composetest.core.ui.bases.BaseViewModel
import com.composetest.core.ui.enums.Permission
import com.composetest.core.ui.providers.PermissionProvider
import com.composetest.feature.weatherforecast.analytics.weatherforecast.WeatherForecastScreenAnalytic
import com.composetest.feature.weatherforecast.enums.WeatherForecastScreenStatus
import com.composetest.feature.weatherforecast.enums.WeatherForecastStatus
import com.composetest.feature.weatherforecast.mappers.FutureWeatherForecastScreenModelsMapper
import com.composetest.feature.weatherforecast.mappers.WeatherNowScreenModelMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class WeatherForecastViewModel @Inject constructor(
    private val getWeatherNowUseCase: GetWeatherNowUseCase,
    private val getWeatherForecastsUseCase: GetWeatherForecastsUseCase,
    private val getTodayWeatherForecastUseCase: GeTodayWeatherForecastUseCase,
    private val getFutureWeatherForecastsUseCase: GetFutureWeatherForecastUseCase,
    private val weatherNowScreenModelMapper: WeatherNowScreenModelMapper,
    private val futureWeatherForecastScreenModelsMapper: FutureWeatherForecastScreenModelsMapper,
    private val locationProvider: LocationProvider,
    private val permissionProvider: PermissionProvider,
    override val sendAnalyticsUseCase: SendAnalyticsUseCase,
    @NavGraphQualifier(NavGraph.MAIN) override val navigationManager: NavigationManager
) : BaseViewModel<WeatherForecastUiState, WeatherForecastUiEvent>(WeatherForecastUiState()),
    WeatherForecastCommandReceiver {

    private var location: Location? = null

    override val commandReceiver = this
    override val analyticScreen = WeatherForecastScreenAnalytic

    init {
        openScreenAnalytic()
        checkPermissions()
    }

    override fun checkPermissionsResult() {
        if (uiState.value.screenStatus == WeatherForecastScreenStatus.READY) return
        if (permissionProvider.somePermissionIsGranted(Permission.localization)) {
            updateUiState { it.setScreenStatus(WeatherForecastScreenStatus.READY) }
            getLocationAndWeatherForecastsData()
        } else {
            updateUiState { it.setScreenStatus(WeatherForecastScreenStatus.PERMISSION_NOT_GRANTED) }
        }
    }

    override fun getLocationAndWeatherForecastsData() {
        runAsyncTask(onError = { error -> updateUiState { it.setLocationError(error) } }) {
            location = locationProvider.getLastLocation()
            getWeatherForecastNowData()
            getWeatherForecastsData()
        }
    }

    override fun dismissSimpleDialog() {
        updateUiState { it.dismissSimpleDialog() }
    }

    private fun checkPermissions() {
        if (!permissionProvider.permissionIsGranted(Permission.localization)) {
            launchUiEvent(WeatherForecastUiEvent.LaunchPermissionRequest)
        }
    }

    private fun getWeatherForecastNowData() = location?.let { location ->
        updateUiState { it.copy(weatherNowStatus = WeatherForecastStatus.LOADING) }
        runAsyncTask(onError = { error -> updateUiState { it.setWeatherNowError(error) } }) {
            val weatherNowForecast = getWeatherNowUseCase(location.latitude, location.longitude)
            updateUiState { uiState ->
                uiState.setWeatherNow(weatherNowScreenModelMapper(weatherNowForecast))
            }
        }
    }

    private fun getWeatherForecastsData() = location?.let { location ->
        updateUiState { it.copy(weatherForecastsStatus = WeatherForecastStatus.LOADING) }
        runAsyncTask(onError = { error -> updateUiState { it.setWeatherForecastsError(error) } }) {
            val weatherForecast =
                getWeatherForecastsUseCase(location.latitude, location.longitude)
            val todayWeatherForecast = getTodayWeatherForecastUseCase(weatherForecast)
            val futureWeatherForecasts = getFutureWeatherForecastsUseCase(weatherForecast)
            updateUiState { uiState ->
                uiState.setWeatherForecasts(
                    todayWeatherForecast,
                    futureWeatherForecastScreenModelsMapper(futureWeatherForecasts)
                )
            }
        }
    }
}