@file:OptIn(ExperimentalPermissionsApi::class)

package com.composetest.feature.weatherforecast.ui.weatherforecast

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import com.composetest.common.extensions.navigateToApplicationDetailSettings
import com.composetest.core.designsystem.components.asyncimage.AsyncImage
import com.composetest.core.designsystem.components.buttons.Button
import com.composetest.core.designsystem.components.buttons.TryAgainButton
import com.composetest.core.designsystem.components.dialogs.SimpleDialog
import com.composetest.core.designsystem.components.graphics.SimpleScatterPlotGraphic
import com.composetest.core.designsystem.components.lifecycle.LifecycleEvent
import com.composetest.core.designsystem.components.shimmer.ShimmerEffect
import com.composetest.core.designsystem.components.topbar.LeftTopBar
import com.composetest.core.designsystem.dimensions.Spacing
import com.composetest.core.designsystem.extensions.screenMargin
import com.composetest.core.designsystem.theme.ComposeTestTheme
import com.composetest.core.ui.enums.Permission
import com.composetest.core.ui.interfaces.Command
import com.composetest.core.ui.interfaces.Screen
import com.composetest.core.ui.utils.getMultiplePermissionState
import com.composetest.feature.weatherforecast.R
import com.composetest.feature.weatherforecast.enums.WeatherForecastScreenStatus
import com.composetest.feature.weatherforecast.enums.WeatherForecastStatus
import com.composetest.feature.weatherforecast.models.FutureDailyWeatherForecastScreenModel
import com.composetest.feature.weatherforecast.models.FutureWeatherForecastScreenModel
import com.composetest.feature.weatherforecast.models.WeatherNowScreenModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import kotlinx.coroutines.flow.Flow
import com.composetest.feature.weatherforecast.R as WeatherForecastResources

internal object WeatherForecastScreen :
    Screen<WeatherForecastUiState, WeatherForecastUiEvent, WeatherForecastCommandReceiver> {

    @Composable
    override operator fun invoke(
        uiState: WeatherForecastUiState,
        uiEvent: Flow<WeatherForecastUiEvent>?,
        onExecuteCommand: (Command<WeatherForecastCommandReceiver>) -> Unit
    ) {
        val permissionState = getMultiplePermissionState(Permission.localization)
        LaunchedEffectHandler(uiEvent = uiEvent, permissionState = permissionState)
        DialogsHandler(uiState = uiState, onExecuteCommand = onExecuteCommand)
        LifecycleEventHandler(onExecuteCommand = onExecuteCommand)
        Column(modifier = Modifier.fillMaxSize()) {
            LeftTopBar(
                titleId = WeatherForecastResources.string.weather_forecast_title,
                actionIcons = uiState.refreshButton,
                onClickAction = { onExecuteCommand(WeatherForecastCommand.GetLocationAndWeatherForecastsData) }
            )
            Column(
                modifier = Modifier.screenMargin(),
                verticalArrangement = Arrangement.spacedBy(Spacing.twentyFour)
            ) {
                if (uiState.showFullScreenMsg) {
                    FullScreenMessage(
                        uiState = uiState,
                        onExecuteCommand = onExecuteCommand,
                        permissionState = permissionState
                    )
                    return
                }
                WeatherNow(uiState = uiState, onExecuteCommand = onExecuteCommand)
                WeatherForecasts(uiState = uiState, onExecuteCommand = onExecuteCommand)
            }
        }
    }
}

// region Full Screen Message
@Composable
private fun FullScreenMessage(
    uiState: WeatherForecastUiState,
    onExecuteCommand: (Command<WeatherForecastCommandReceiver>) -> Unit,
    permissionState: MultiplePermissionsState,
) {
    Box(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(Spacing.eight),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (uiState.screenStatus == WeatherForecastScreenStatus.PERMISSION_NOT_GRANTED) {
                Text(
                    text = stringResource(R.string.weather_forecast_required_permission_msg),
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center
                )
                NeedsPermissionButton(permissionState = permissionState)
            } else {
                TryAgainButton(
                    modifier = Modifier
                        .background(color = MaterialTheme.colorScheme.surface)
                        .fillMaxSize(),
                ) {
                    onExecuteCommand(WeatherForecastCommand.GetLocationAndWeatherForecastsData)
                }
            }
        }
    }
}

@Composable
private fun NeedsPermissionButton(permissionState: MultiplePermissionsState) {
    val context = LocalContext.current
    val buttonText = if (permissionState.shouldShowRationale) {
        WeatherForecastResources.string.weather_forecast_active_permissions
    } else {
        WeatherForecastResources.string.weather_forecast_active_permissions_blocked
    }
    Button(text = stringResource(buttonText)) {
        if (permissionState.shouldShowRationale) {
            permissionState.launchMultiplePermissionRequest()
        } else {
            context.navigateToApplicationDetailSettings()
        }
    }
}
// endregion

// region Weather Now
@Composable
private fun WeatherNow(
    uiState: WeatherForecastUiState,
    onExecuteCommand: (Command<WeatherForecastCommandReceiver>) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        when (uiState.weatherNowStatus) {
            WeatherForecastStatus.LOADING -> WeatherNowShimmer()
            WeatherForecastStatus.ERROR -> TryAgainButton(
                modifier = Modifier
                    .fillMaxHeight(0.1f)
                    .fillMaxWidth(),
            ) { onExecuteCommand(WeatherForecastCommand.GetWeatherForecastNowData) }
            else -> WeatherNowContent(uiState = uiState)
        }
    }
}

@Composable
private fun WeatherNowContent(uiState: WeatherForecastUiState) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(Spacing.twelve)
    ) {
        Column(
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.spacedBy(Spacing.eight)
        ) {
            Text(
                text = uiState.weatherNow.city,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.widthIn(max = 150.dp),
                textAlign = TextAlign.Right,
                maxLines = 2
            )
            Text(
                modifier = Modifier.widthIn(max = 180.dp),
                text = uiState.weatherNow.description,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.End
            )
        }
        Text(
            text = uiState.weatherNow.temperature,
            style = MaterialTheme.typography.displaySmall
        )
        AsyncImage(
            modifier = Modifier.scale(2f),
            url = uiState.weatherNow.iconUrl,
            alignment = Alignment.Center
        )
    }
}
// endregion

// region Weather Forecasts
@Composable
private fun WeatherForecasts(
    uiState: WeatherForecastUiState,
    onExecuteCommand: (Command<WeatherForecastCommandReceiver>) -> Unit,
) {
    when (uiState.weatherForecastsStatus) {
        WeatherForecastStatus.LOADING -> WeatherForecastsShimmer()
        WeatherForecastStatus.ERROR -> TryAgainButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
        ) { onExecuteCommand(WeatherForecastCommand.GetWeatherForecastsData) }
        else -> WeatherForecastsContent(uiState = uiState)
    }
}

@Composable
private fun WeatherForecastsContent(uiState: WeatherForecastUiState) {
    WeatherForecastGraphic(uiState = uiState)
    LazyColumn(verticalArrangement = Arrangement.spacedBy(Spacing.twelve)) {
        items(uiState.futureWeatherForecasts) {
            FutureWeatherForecast(futureWeatherForecastScreen = it)
        }
        item {
            Spacer(Modifier.windowInsetsPadding(WindowInsets.navigationBars))
        }
    }
}

@Composable
private fun WeatherForecastGraphic(uiState: WeatherForecastUiState) {
    Card {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentAlignment = Alignment.Center
        ) {
            if (uiState.todayWeatherForecast != null) {
                SimpleScatterPlotGraphic(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(Spacing.twelve),
                    yPoints = uiState.todayWeatherForecast.temperatures,
                    minLabel = uiState.todayWeatherForecast.minTemperature,
                    maxLabel = uiState.todayWeatherForecast.maxTemperature,
                    labelFormat = "%sº"
                )
            } else {
                Text(
                    text = stringResource(WeatherForecastResources.string.weather_forecast_unavailable_forecast_msg),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

@Composable
private fun FutureWeatherForecast(futureWeatherForecastScreen: FutureWeatherForecastScreenModel) {
    Column(verticalArrangement = Arrangement.spacedBy(Spacing.eight)) {
        Text(
            text = futureWeatherForecastScreen.day,
            style = MaterialTheme.typography.titleLarge
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            futureWeatherForecastScreen.futureDailyWeatherForecasts.forEach {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    AsyncImage(modifier = Modifier.scale(1.5f), url = it.iconUrl)
                    Text(
                        text = it.temperature,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Spacer(Modifier.padding(vertical = Spacing.four))
                    Text(
                        text = it.hour,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}
// endregion

// region Shimmers
@Composable
private fun WeatherNowShimmer() {
    ShimmerEffect(
        modifier = Modifier
            .fillMaxHeight(0.07f)
            .fillMaxWidth(0.6f)
    )
}

@Composable
private fun WeatherForecastsShimmer() {
    ShimmerEffect(
        modifier = Modifier
            .clip(MaterialTheme.shapes.medium)
            .fillMaxWidth()
            .height(200.dp)
    )
    Column(verticalArrangement = Arrangement.spacedBy(Spacing.twelve)) {
        repeat(4) {
            Column(verticalArrangement = Arrangement.spacedBy(Spacing.sixteen)) {
                ShimmerEffect(
                    modifier = Modifier
                        .fillMaxWidth(0.6f)
                        .height(30.dp)
                )
                ShimmerEffect(
                    modifier = Modifier
                        .padding(horizontal = Spacing.eight)
                        .height(70.dp)
                        .fillMaxWidth()
                )
            }
        }
    }
}
// endregion

// region Handlers
@Composable
private fun DialogsHandler(
    uiState: WeatherForecastUiState,
    onExecuteCommand: (Command<WeatherForecastCommandReceiver>) -> Unit
) = uiState.simpleDialogParam?.let {
    SimpleDialog(param = it) {
        onExecuteCommand(WeatherForecastCommand.DismissSimpleDialog)
    }
}

@Composable
private fun LaunchedEffectHandler(
    uiEvent: Flow<WeatherForecastUiEvent>?,
    permissionState: MultiplePermissionsState,
) {
    LaunchedEffect(Unit) {
        uiEvent?.collect {
            when (it) {
                WeatherForecastUiEvent.LaunchPermissionRequest -> {
                    permissionState.launchMultiplePermissionRequest()
                }
            }
        }
    }
}

@Composable
private fun LifecycleEventHandler(
    onExecuteCommand: (Command<WeatherForecastCommandReceiver>) -> Unit
) {
    LifecycleEvent {
        if (it == Lifecycle.Event.ON_RESUME) {
            onExecuteCommand(WeatherForecastCommand.CheckPermissionsResult)
        }
    }
}
// endregion

@Composable
@Preview
private fun Preview() {
    ComposeTestTheme {
        WeatherForecastScreen(
            uiState = WeatherForecastUiState(
                screenStatus = WeatherForecastScreenStatus.READY,
                weatherNowStatus = WeatherForecastStatus.READY,
                weatherForecastsStatus = WeatherForecastStatus.READY,
                weatherNow = WeatherNowScreenModel(
                    city = "Porto",
                    description = "Nuvens carregadas e trovoadas",
                    temperature = "30º",
                    iconUrl = ""
                ),
                futureWeatherForecasts = listOf(
                    FutureWeatherForecastScreenModel(
                        day = "Segunda",
                        futureDailyWeatherForecasts = listOf(
                            FutureDailyWeatherForecastScreenModel(
                                temperature = "30º",
                                hour = "12:00",
                                iconUrl = ""
                            ),
                            FutureDailyWeatherForecastScreenModel(
                                temperature = "30º",
                                hour = "12:00",
                                iconUrl = ""
                            ),
                            FutureDailyWeatherForecastScreenModel(
                                temperature = "30º",
                                hour = "12:00",
                                iconUrl = ""
                            ),
                            FutureDailyWeatherForecastScreenModel(
                                temperature = "30º",
                                hour = "12:00",
                                iconUrl = ""
                            )
                        )
                    )
                )
            ),
            uiEvent = null
        ) { }
    }
}