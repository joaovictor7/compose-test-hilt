package com.composetest.feature.weatherforecast.presenter.ui

import android.location.Location
import androidx.lifecycle.viewModelScope
import com.composetest.core.analytic.AnalyticSender
import com.composetest.core.analytic.events.CommonAnalyticEvent
import com.composetest.core.designsystem.components.dialogs.CommonSimpleDialog
import com.composetest.core.designsystem.utils.getCommonSimpleDialogErrorParam
import com.composetest.core.ui.bases.BaseViewModel
import com.composetest.core.ui.di.qualifiers.AsyncTaskUtilsQualifier
import com.composetest.core.ui.enums.Permission
import com.composetest.core.ui.interfaces.UiEvent
import com.composetest.core.ui.interfaces.UiState
import com.composetest.core.ui.providers.LocationProvider
import com.composetest.core.ui.providers.PermissionProvider
import com.composetest.core.ui.utils.AsyncTaskUtils
import com.composetest.feature.weatherforecast.analytic.screens.WeatherForecastScreenAnalytic
import com.composetest.feature.weatherforecast.domain.usescases.GetFutureWeatherForecastUseCase
import com.composetest.feature.weatherforecast.domain.usescases.GetTodayWeatherForecastUseCase
import com.composetest.feature.weatherforecast.domain.usescases.GetWeatherForecastsUseCase
import com.composetest.feature.weatherforecast.domain.usescases.GetWeatherNowUseCase
import com.composetest.feature.weatherforecast.presenter.enums.WeatherForecastScreenStatus
import com.composetest.feature.weatherforecast.presenter.enums.WeatherForecastStatus
import com.composetest.feature.weatherforecast.presenter.mappers.FutureWeatherForecastScreenModelsMapper
import com.composetest.feature.weatherforecast.presenter.mappers.WeatherNowScreenModelMapper
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
    private val getTodayWeatherForecastUseCase: GetTodayWeatherForecastUseCase,
    private val getFutureWeatherForecastsUseCase: GetFutureWeatherForecastUseCase,
    private val weatherNowScreenModelMapper: WeatherNowScreenModelMapper,
    private val futureWeatherForecastScreenModelsMapper: FutureWeatherForecastScreenModelsMapper,
    private val locationProvider: LocationProvider,
    private val permissionProvider: PermissionProvider,
    private val analyticSender: AnalyticSender,
    @AsyncTaskUtilsQualifier(WeatherForecastScreenAnalytic.WEATHER_FORECAST) private val asyncTaskUtils: AsyncTaskUtils,
) : BaseViewModel(), UiState<WeatherForecastUiState>, UiEvent<WeatherForecastUiEvent>,
    WeatherForecastCommandReceiver {

    override val commandReceiver = this

    private var location: Location? = null
    private var weatherForecastNowWasGet = false
    private var weatherForecastsWasGet = false

    private val _uiState = MutableStateFlow(WeatherForecastUiState())
    override val uiState = _uiState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<WeatherForecastUiEvent>(replay = 1)
    override val uiEvent = _uiEvent.asSharedFlow()

    init {
        sendOpenScreenAnalytic()
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
        asyncTaskUtils.runAsyncTask(viewModelScope, onError = ::handleLocationError) {
            location = locationProvider.getLastLocation()
            getWeatherForecastNowData()
            getWeatherForecastsData()
        }
    }

    override fun getWeatherForecastNowData() {
        location?.let { location ->
            _uiState.update { it.copy(weatherNowStatus = WeatherForecastStatus.LOADING) }
            asyncTaskUtils.runAsyncTask(viewModelScope, onError = ::handleWeatherForecastNowError) {
                val weatherNowForecast =
                    getWeatherNowUseCase(location.latitude, location.longitude)
                _uiState.update {
                    it.setWeatherNow(
                        weatherNowScreenModelMapper.mapperToModel(
                            weatherNowForecast
                        )
                    )
                }
                weatherForecastNowWasGet = true
            }
        }
    }

    override fun getWeatherForecastsData() {
        location?.let { location ->
            _uiState.update { it.copy(weatherForecastsStatus = WeatherForecastStatus.LOADING) }
            asyncTaskUtils.runAsyncTask(viewModelScope, onError = ::handleWeatherForecastsError) {
                val weatherForecast =
                    getWeatherForecastsUseCase(location.latitude, location.longitude)
                val todayWeatherForecast = getTodayWeatherForecastUseCase(weatherForecast)
                val futureWeatherForecasts = getFutureWeatherForecastsUseCase(weatherForecast)
                _uiState.update { uiState ->
                    uiState.setWeatherForecasts(
                        todayWeatherForecast,
                        futureWeatherForecastScreenModelsMapper.mapperToModels(
                            futureWeatherForecasts
                        )
                    )
                }
                weatherForecastsWasGet = true
            }
        }
    }

    override fun dismissSimpleDialog() {
        _uiState.update { it.dismissSimpleDialog() }
    }

    override fun sendOpenScreenAnalytic() = asyncTaskUtils.runAsyncTask(viewModelScope) {
        analyticSender.sendEvent(CommonAnalyticEvent.OpenScreen(WeatherForecastScreenAnalytic))
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