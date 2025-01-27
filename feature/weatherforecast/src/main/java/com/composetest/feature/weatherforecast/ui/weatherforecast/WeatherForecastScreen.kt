@file:OptIn(ExperimentalPermissionsApi::class)

package com.composetest.feature.weatherforecast.ui.weatherforecast

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.composetest.core.designsystem.components.asyncimage.AsyncImage
import com.composetest.core.designsystem.components.buttons.Button
import com.composetest.core.designsystem.components.dialogs.SimpleDialog
import com.composetest.core.designsystem.components.graphics.SimpleScatterPlotGraphic
import com.composetest.core.designsystem.components.topbar.LeftTopBar
import com.composetest.core.designsystem.dimensions.Spacing
import com.composetest.core.designsystem.extensions.horizontalScreenMargin
import com.composetest.core.designsystem.theme.ComposeTestTheme
import com.composetest.common.extensions.navigateToApplicationDetailSettings
import com.composetest.core.ui.enums.Permission
import com.composetest.core.ui.interfaces.Command
import com.composetest.core.ui.interfaces.Screen
import com.composetest.feature.weatherforecast.models.FutureWeatherForecastScreenModel
import com.composetest.feature.weatherforecast.models.WeatherNowScreenModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import kotlinx.coroutines.flow.Flow
import com.composetest.core.designsystem.R as DesignSystemResources
import com.composetest.feature.weatherforecast.R as WeatherForecastResources

internal object WeatherForecastScreen :
    Screen<WeatherForecastUiState, WeatherForecastUiEvent, WeatherForecastCommandReceiver> {

    @Composable
    override operator fun invoke(
        uiState: WeatherForecastUiState,
        uiEvent: Flow<WeatherForecastUiEvent>?,
        onExecuteCommand: (Command<WeatherForecastCommandReceiver>) -> Unit
    ) {
        val permissionState = rememberMultiplePermissionsState(
            permissions = Permission.localization,
            onPermissionsResult = {
                onExecuteCommand(WeatherForecastCommand.CheckPermissionsResult(it))
            }
        )
        LaunchedEffectHandler(
            permissionState = permissionState,
            onExecuteCommand = onExecuteCommand
        )
        AlertDialogHandler(uiState = uiState, onExecuteCommand = onExecuteCommand)
        Column(modifier = Modifier.windowInsetsPadding(WindowInsets.statusBars)) {
            LeftTopBar(titleId = WeatherForecastResources.string.weather_forecast_title)
            Column(
                modifier = Modifier
                    .horizontalScreenMargin()
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(Spacing.twentyFour)
            ) {
                if (!uiState.thereIsPermission) {
                    RequiredPermission(permissionState = permissionState)
                    return
                }
                WeatherNow(uiState = uiState, onExecuteCommand = onExecuteCommand)
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
        }
    }
}

@Composable
private fun RequiredPermission(permissionState: MultiplePermissionsState) {
    val context = LocalContext.current
    val buttonText = if (permissionState.shouldShowRationale) {
        WeatherForecastResources.string.weather_forecast_active_permissions
    } else {
        WeatherForecastResources.string.weather_forecast_active_permissions_blocked
    }
    Box(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(Spacing.eight),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(WeatherForecastResources.string.weather_forecast_required_permission_msg),
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center
            )
            Button(text = stringResource(buttonText)) {
                if (permissionState.shouldShowRationale) {
                    permissionState.launchMultiplePermissionRequest()
                } else {
                    context.navigateToApplicationDetailSettings()
                }
            }
        }
    }
}

@Composable
private fun RefreshButton(
    modifier: Modifier,
    uiState: WeatherForecastUiState,
    onExecuteCommand: (Command<WeatherForecastCommandReceiver>) -> Unit
) {
    Box(
        modifier = modifier.padding(end = Spacing.twelve),
        contentAlignment = AbsoluteAlignment.CenterRight
    ) {
        if (uiState.isLoading) {
            CircularProgressIndicator(modifier = Modifier.size(24.dp))
        } else {
            IconButton(onClick = { onExecuteCommand(WeatherForecastCommand.GetWeatherForecastData) }) {
                Icon(
                    painter = painterResource(DesignSystemResources.drawable.ic_refresh_medium),
                    contentDescription = null
                )
            }
        }
    }
}

@Composable
private fun WeatherNow(
    uiState: WeatherForecastUiState,
    onExecuteCommand: (Command<WeatherForecastCommandReceiver>) -> Unit
) = with(uiState.weatherNow) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier.weight(0.75f),
            horizontalArrangement = Arrangement.End
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(Spacing.twelve)
            ) {
                Column(
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.spacedBy(Spacing.eight)
                ) {
                    Text(
                        text = city,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.widthIn(max = 150.dp),
                        textAlign = TextAlign.Right,
                        maxLines = 2
                    )
                    Text(
                        modifier = Modifier.widthIn(max = 180.dp),
                        text = description,
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.End
                    )
                }
                Text(
                    text = temperature,
                    style = MaterialTheme.typography.displaySmall
                )
                AsyncImage(
                    modifier = Modifier.scale(2f),
                    url = iconUrl,
                    alignment = Alignment.Center
                )
            }
        }
        RefreshButton(
            modifier = Modifier.weight(0.25f),
            uiState = uiState,
            onExecuteCommand = onExecuteCommand
        )
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

@Composable
private fun AlertDialogHandler(
    uiState: WeatherForecastUiState,
    onExecuteCommand: (Command<WeatherForecastCommandReceiver>) -> Unit
) = uiState.simpleDialogParam?.let {
    SimpleDialog(param = it) {
        onExecuteCommand(WeatherForecastCommand.DismissSimpleDialog)
    }
}

@Composable
private fun LaunchedEffectHandler(
    permissionState: MultiplePermissionsState,
    onExecuteCommand: (Command<WeatherForecastCommandReceiver>) -> Unit
) {
    LaunchedEffect(Unit) {
        if (!permissionState.allPermissionsGranted) {
            permissionState.launchMultiplePermissionRequest()
        }
    }
}

@Composable
@Preview
private fun Preview() {
    ComposeTestTheme {
        WeatherForecastScreen(
            uiState = WeatherForecastUiState(
                weatherNow = WeatherNowScreenModel(
                    city = "Porto",
                    description = "Nuvens carregadas e trovoadas",
                    temperature = "30º",
                    iconUrl = ""
                )
            ),
            uiEvent = null
        ) { }
    }
}