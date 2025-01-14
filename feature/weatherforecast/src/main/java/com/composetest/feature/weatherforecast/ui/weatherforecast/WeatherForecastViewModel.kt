package com.composetest.feature.weatherforecast.ui.weatherforecast

import androidx.lifecycle.viewModelScope
import com.composetest.common.managers.AsyncTaskManager
import com.composetest.core.designsystem.utils.getDefaultSimpleDialogErrorParam
import com.composetest.core.domain.usecases.GeTodayWeatherForecastUseCase
import com.composetest.core.domain.usecases.GetFutureWeatherForecastUseCase
import com.composetest.core.domain.usecases.GetWeatherForecastsUseCase
import com.composetest.core.domain.usecases.GetWeatherNowUseCase
import com.composetest.core.router.di.qualifiers.NavGraphQualifier
import com.composetest.core.router.enums.NavGraph
import com.composetest.core.router.managers.NavigationManager
import com.composetest.core.ui.bases.BaseViewModel
import com.composetest.feature.weatherforecast.mappers.FutureWeatherForecastScreenModelsMapper
import com.composetest.feature.weatherforecast.mappers.WeatherNowScreenModelMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class WeatherForecastViewModel @Inject constructor(
    private val getWeatherNowUseCase: GetWeatherNowUseCase,
    private val getWeatherForecastsUseCase: GetWeatherForecastsUseCase,
    private val getTodayWeatherForecastUseCase: GeTodayWeatherForecastUseCase,
    private val getFutureWeatherForecastsUseCase: GetFutureWeatherForecastUseCase,
    private val weatherNowScreenModelMapper: WeatherNowScreenModelMapper,
    private val futureWeatherForecastScreenModelsMapper: FutureWeatherForecastScreenModelsMapper,
    private val asyncTaskManager: AsyncTaskManager,
    @NavGraphQualifier(NavGraph.MAIN) override val navigationManager: NavigationManager
) : BaseViewModel<WeatherForecastUiState, WeatherForecastUiEvent>(WeatherForecastUiState()),
    WeatherForecastCommandReceiver {

    override val commandReceiver = this

    override fun initUiState() {

        getWeatherForecastData()
    }

    override fun refresh() {
        getWeatherForecastData()
    }

    override fun dismissSimpleDialog() {
        updateUiState { it.setSimpleAlertDialog(null) }
    }

    private fun getWeatherForecastData() {
        updateUiState { it.setLoading(true) }
        viewModelScope.launch {
            asyncTaskManager.runAsyncTask(
                onError = ::handleRequestError,
                onCompletion = { updateUiState { it.setLoading(false) } }
            ) {
                setWeatherNow()
                setWeatherForecasts()
            }
        }
    }

    private suspend fun setWeatherNow() {
        val weatherNowForecast = getWeatherNowUseCase()
        updateUiState {
            it.setWeatherNow(weatherNowScreenModelMapper(weatherNowForecast))
        }
    }

    private suspend fun setWeatherForecasts() {
        val weatherForecast = getWeatherForecastsUseCase()
        val todayWeatherForecast = getTodayWeatherForecastUseCase(weatherForecast)
        val futureWeatherForecasts = getFutureWeatherForecastsUseCase(weatherForecast)
        updateUiState {
            it.setWeatherForecasts(
                todayWeatherForecast,
                futureWeatherForecastScreenModelsMapper(futureWeatherForecasts)
            )
        }
    }

    private fun handleRequestError(error: Throwable) {
        updateUiState { uiState ->
            uiState.setSimpleAlertDialog(getDefaultSimpleDialogErrorParam(error))
        }
    }
}