package com.composetest.feature.weatherforecast.ui.weatherforecast

import androidx.compose.runtime.Composable
import com.composetest.core.ui.interfaces.Command
import com.composetest.core.ui.interfaces.Screen
import kotlinx.coroutines.flow.Flow

internal object WeatherForecastScreen :
    Screen<WeatherForecastUiState, WeatherForecastUiEvent, WeatherForecastCommandReceiver> {

    @Composable
    override fun invoke(
        uiState: WeatherForecastUiState,
        uiEvent: Flow<WeatherForecastUiEvent>?,
        onExecuteCommand: (Command<WeatherForecastCommandReceiver>) -> Unit
    ) {

    }
}