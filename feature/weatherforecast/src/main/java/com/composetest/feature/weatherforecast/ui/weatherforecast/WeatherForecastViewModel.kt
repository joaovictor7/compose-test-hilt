package com.composetest.feature.weatherforecast.ui.weatherforecast

import android.location.Location
import com.composetest.common.providers.LocationProvider
import com.composetest.core.designsystem.components.dialogs.CommonSimpleDialog
import com.composetest.core.designsystem.utils.getCommonSimpleDialogErrorParam
import com.composetest.core.domain.usecases.GeTodayWeatherForecastUseCase
import com.composetest.core.domain.usecases.GetFutureWeatherForecastUseCase
import com.composetest.core.domain.usecases.GetWeatherForecastsUseCase
import com.composetest.core.domain.usecases.GetWeatherNowUseCase
import com.composetest.core.domain.usecases.SendAnalyticsUseCase
import com.composetest.core.ui.bases.BaseViewModel
import com.composetest.core.ui.enums.Permission
import com.composetest.core.ui.interfaces.UiEvent
import com.composetest.core.ui.interfaces.UiState
import com.composetest.core.ui.providers.PermissionProvider
import com.composetest.feature.weatherforecast.analytics.weatherforecast.WeatherForecastScreenAnalytic
import com.composetest.feature.weatherforecast.enums.WeatherForecastScreenStatus
import com.composetest.feature.weatherforecast.enums.WeatherForecastStatus
import com.composetest.feature.weatherforecast.mappers.FutureWeatherForecastScreenModelsMapper
import com.composetest.feature.weatherforecast.mappers.WeatherNowScreenModelMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
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
) : BaseViewModel(), UiState<WeatherForecastUiState>, UiEvent<WeatherForecastUiEvent>,
    WeatherForecastCommandReceiver {

    private val _uiState = MutableStateFlow(WeatherForecastUiState())
    private val _uiEvent = MutableSharedFlow<WeatherForecastUiEvent>()
    private var location: Location? = null
    private var weatherForecastNowWasGet = false
    private var weatherForecastsWasGet = false

    override val uiState = _uiState.asStateFlow()
    override val uiEvent = _uiEvent.asSharedFlow()
    override val commandReceiver = this
    override val analyticScreen = WeatherForecastScreenAnalytic

    init {
        openScreenAnalytic()
        checkPermissions()
    }

    override fun checkPermissionsResult() {
        if (uiState.value.screenStatus == WeatherForecastScreenStatus.READY) return
        if (permissionProvider.somePermissionIsGranted(Permission.localization)) {
            _uiState.update { it.setScreenStatus(WeatherForecastScreenStatus.READY) }
            getLocationAndWeatherForecastsData()
        } else {
            _uiState.update { it.setScreenStatus(WeatherForecastScreenStatus.PERMISSION_NOT_GRANTED) }
        }
    }

    override fun getLocationAndWeatherForecastsData() {
        runAsyncTask(onError = ::handleLocationError) {
            location = locationProvider.getLastLocation()
            getWeatherForecastNowData()
            getWeatherForecastsData()
        }
    }

    override fun getWeatherForecastNowData() {
        location?.let { location ->
            _uiState.update { it.copy(weatherNowStatus = WeatherForecastStatus.LOADING) }
            runAsyncTask(onError = ::handleWeatherForecastNowError) {
                val weatherNowForecast = getWeatherNowUseCase(location.latitude, location.longitude)
                _uiState.update { uiState ->
                    uiState.setWeatherNow(weatherNowScreenModelMapper(weatherNowForecast))
                }
                weatherForecastNowWasGet = true
            }
        }
    }

    override fun getWeatherForecastsData() {
        location?.let { location ->
            _uiState.update { it.copy(weatherForecastsStatus = WeatherForecastStatus.LOADING) }
            runAsyncTask(onError = ::handleWeatherForecastsError) {
                val weatherForecast =
                    getWeatherForecastsUseCase(location.latitude, location.longitude)
                val todayWeatherForecast = getTodayWeatherForecastUseCase(weatherForecast)
                val futureWeatherForecasts = getFutureWeatherForecastsUseCase(weatherForecast)
                _uiState.update { uiState ->
                    uiState.setWeatherForecasts(
                        todayWeatherForecast,
                        futureWeatherForecastScreenModelsMapper(futureWeatherForecasts)
                    )
                }
                weatherForecastsWasGet = true
            }
        }
    }

    override fun dismissSimpleDialog() {
        _uiState.update { it.dismissSimpleDialog() }
    }

    private fun checkPermissions() {
        if (!permissionProvider.permissionIsGranted(Permission.localization)) {
            _uiEvent.emitEvent(WeatherForecastUiEvent.LaunchPermissionRequest)
        }
    }

    private fun handleLocationError(error: Throwable?) = _uiState.update {
        it.setLocationError(getCommonSimpleDialogErrorParam(error))
    }

    private fun handleWeatherForecastNowError(error: Throwable?) = _uiState.update {
        val (status, simpleDialog) = getStatusAndErrorDialog(error, weatherForecastNowWasGet)
        it.setWeatherNowError(status, simpleDialog)
    }

    private fun handleWeatherForecastsError(error: Throwable?) = _uiState.update {
        val (status, simpleDialog) = getStatusAndErrorDialog(error, weatherForecastsWasGet)
        it.setWeatherForecastsError(status, simpleDialog)
    }

    private fun getStatusAndErrorDialog(error: Throwable?, dataWasGet: Boolean) = if (dataWasGet) {
        WeatherForecastStatus.READY to getCommonSimpleDialogErrorParam(
            error,
            CommonSimpleDialog.FailedUpdateError
        )
    } else {
        WeatherForecastStatus.ERROR to getCommonSimpleDialogErrorParam(error)
    }
}