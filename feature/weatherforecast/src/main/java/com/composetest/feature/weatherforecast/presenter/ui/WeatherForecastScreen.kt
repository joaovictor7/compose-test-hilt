@file:OptIn(ExperimentalPermissionsApi::class)

package com.composetest.feature.weatherforecast.presenter.ui

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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.composetest.core.designsystem.component.asyncimage.AsyncImage
import com.composetest.core.designsystem.component.button.Button
import com.composetest.core.designsystem.component.button.TryAgainButton
import com.composetest.core.designsystem.component.graphic.SimpleScatterPlotGraphic
import com.composetest.core.designsystem.component.lifecycle.LifecycleEvent
import com.composetest.core.designsystem.component.shimmer.Shimmer
import com.composetest.core.designsystem.component.topbar.LeftTopBar
import com.composetest.core.designsystem.dimension.Spacing
import com.composetest.core.designsystem.extension.screenMargin
import com.composetest.core.designsystem.extension.stringResource
import com.composetest.core.designsystem.theme.ComposeTestTheme
import com.composetest.core.designsystem.util.getSharedShimmerOffset
import com.composetest.core.router.extension.navigateTo
import com.composetest.core.ui.enums.Permission
import com.composetest.core.ui.extension.navigateToApplicationDetailSettings
import com.composetest.core.ui.interfaces.Intent
import com.composetest.core.ui.util.UiEventsObserver
import com.composetest.core.ui.util.getMultiplePermissionState
import com.composetest.feature.weatherforecast.presenter.enums.WeatherForecastScreenStatus
import com.composetest.feature.weatherforecast.presenter.enums.WeatherForecastStatus
import com.composetest.feature.weatherforecast.presenter.model.FutureDailyWeatherForecastScreenModel
import com.composetest.feature.weatherforecast.presenter.model.FutureWeatherForecastScreenModel
import com.composetest.feature.weatherforecast.presenter.model.WeatherNowScreenModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import com.composetest.feature.weatherforecast.R as WeatherForecastResources

@Composable
internal fun WeatherForecastScreen(
    uiState: WeatherForecastUiState,
    uiEvent: Flow<WeatherForecastUiEvent> = emptyFlow(),
    onExecuteIntent: (Intent<WeatherForecastIntentReceiver>) -> Unit = {},
    navController: NavHostController = rememberNavController(),
) {
    val permissionState = getMultiplePermissionState(Permission.localization)
    val shimmerOffset by getSharedShimmerOffset()
    UiEventsHandler(
        uiEvent = uiEvent,
        navController = navController,
        permissionState = permissionState
    )
    LifecycleEventHandler(onExecuteIntent = onExecuteIntent)
    Column(modifier = Modifier.fillMaxSize()) {
        LeftTopBar(
            titleId = WeatherForecastResources.string.weather_forecast_title,
            actionIcons = uiState.refreshButton,
            onClickAction = { onExecuteIntent(WeatherForecastIntent.GetLocationAndWeatherForecastsData) }
        )
        Column(
            modifier = Modifier.screenMargin(),
            verticalArrangement = Arrangement.spacedBy(Spacing.extraLarge)
        ) {
            if (uiState.showFullScreenMsg) {
                FullScreenMessage(
                    uiState = uiState,
                    onExecuteIntent = onExecuteIntent,
                    permissionState = permissionState
                )
                return
            }
            WeatherNow(
                uiState = uiState,
                onExecuteIntent = onExecuteIntent,
                shimmerOffset = shimmerOffset
            )
            WeatherForecasts(
                uiState = uiState,
                onExecuteIntent = onExecuteIntent,
                shimmerOffset = shimmerOffset
            )
        }
    }
}

// region Full Screen Message
@Composable
private fun FullScreenMessage(
    uiState: WeatherForecastUiState,
    onExecuteIntent: (Intent<WeatherForecastIntentReceiver>) -> Unit,
    permissionState: MultiplePermissionsState,
) {
    Box(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(Spacing.small),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (uiState.screenStatusIsTryAgain) {
                TryAgainButton(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.surface)
                        .fillMaxSize(),
                ) {
                    onExecuteIntent(WeatherForecastIntent.GetLocationAndWeatherForecastsData)
                }
            } else {
                Text(
                    text = stringResource(uiState.screenStatus.titleId),
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center
                )
                if (uiState.screenStatusIsPermissionNotGranted) {
                    NeedsPermissionButton(permissionState = permissionState)
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
    onExecuteIntent: (Intent<WeatherForecastIntentReceiver>) -> Unit,
    shimmerOffset: Float,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        when (uiState.weatherNowStatus) {
            WeatherForecastStatus.LOADING -> WeatherNowShimmer(shimmerOffset = shimmerOffset)
            WeatherForecastStatus.ERROR -> TryAgainButton(
                modifier = Modifier
                    .fillMaxHeight(0.1f)
                    .fillMaxWidth(),
            ) { onExecuteIntent(WeatherForecastIntent.GetWeatherForecastNowData) }
            else -> WeatherNowContent(uiState = uiState)
        }
    }
}

@Composable
private fun WeatherNowContent(uiState: WeatherForecastUiState) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(Spacing.medium)
    ) {
        Column(
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.spacedBy(Spacing.small)
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
    onExecuteIntent: (Intent<WeatherForecastIntentReceiver>) -> Unit,
    shimmerOffset: Float,
) {
    when (uiState.weatherForecastsStatus) {
        WeatherForecastStatus.LOADING -> WeatherForecastsShimmer(shimmerOffset = shimmerOffset)
        WeatherForecastStatus.ERROR -> TryAgainButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
        ) { onExecuteIntent(WeatherForecastIntent.GetWeatherForecastsData) }
        else -> WeatherForecastsContent(uiState = uiState)
    }
}

@Composable
private fun WeatherForecastsContent(uiState: WeatherForecastUiState) {
    WeatherForecastGraphic(uiState = uiState)
    LazyColumn(verticalArrangement = Arrangement.spacedBy(Spacing.medium)) {
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
                        .padding(Spacing.medium),
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
    Column(verticalArrangement = Arrangement.spacedBy(Spacing.small)) {
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
                    Spacer(Modifier.padding(vertical = Spacing.tiny))
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
private fun WeatherNowShimmer(shimmerOffset: Float) {
    Shimmer(
        modifier = Modifier
            .fillMaxHeight(0.07f)
            .fillMaxWidth(0.6f),
        offset = shimmerOffset,
    )
}

@Composable
private fun WeatherForecastsShimmer(shimmerOffset: Float) {
    Shimmer(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        offset = shimmerOffset,
    )
    Column(verticalArrangement = Arrangement.spacedBy(Spacing.medium)) {
        repeat(4) {
            Column(verticalArrangement = Arrangement.spacedBy(Spacing.semiLarge)) {
                Shimmer(
                    modifier = Modifier
                        .fillMaxWidth(0.6f)
                        .height(30.dp),
                    offset = shimmerOffset,
                )
                Shimmer(
                    modifier = Modifier
                        .padding(horizontal = Spacing.small)
                        .height(70.dp)
                        .fillMaxWidth(),
                    offset = shimmerOffset,
                )
            }
        }
    }
}
// endregion

// region Handlers
@Composable
private fun UiEventsHandler(
    uiEvent: Flow<WeatherForecastUiEvent>,
    navController: NavHostController,
    permissionState: MultiplePermissionsState,
) {
    UiEventsObserver(uiEvent) {
        when (it) {
            is WeatherForecastUiEvent.LaunchPermissionRequest -> {
                permissionState.launchMultiplePermissionRequest()
            }
            is WeatherForecastUiEvent.NavigateTo -> {
                navController.navigateTo(it.navigationModel)
            }
        }
    }
}

@Composable
private fun LifecycleEventHandler(
    onExecuteIntent: (Intent<WeatherForecastIntentReceiver>) -> Unit
) {
    LifecycleEvent {
        onExecuteIntent(WeatherForecastIntent.OnResume)
    }
}
// endregion

@Composable
@Preview
private fun Preview() {
    ComposeTestTheme {
        WeatherForecastScreen(
            uiState = WeatherForecastUiState(
                screenStatus = WeatherForecastScreenStatus.TRY_AGAIN,
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
        )
    }
}