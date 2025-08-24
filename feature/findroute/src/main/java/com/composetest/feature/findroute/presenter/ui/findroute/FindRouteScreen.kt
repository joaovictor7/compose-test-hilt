@file:OptIn(ExperimentalPermissionsApi::class)

package com.composetest.feature.findroute.presenter.ui.findroute

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.composetest.core.designsystem.component.button.BackButton
import com.composetest.core.designsystem.component.button.Button
import com.composetest.core.designsystem.component.button.TryAgainButton
import com.composetest.core.designsystem.component.lifecycle.LifecycleEvent
import com.composetest.core.designsystem.dimension.Spacing
import com.composetest.core.designsystem.extension.stringResource
import com.composetest.core.designsystem.theme.ComposeTestTheme
import com.composetest.core.ui.enums.Permission
import com.composetest.core.ui.extension.navigateToApplicationDetailSettings
import com.composetest.core.ui.interfaces.Intent
import com.composetest.core.ui.util.UiEventsObserver
import com.composetest.core.ui.util.getMultiplePermissionState
import com.composetest.feature.findroute.R
import com.composetest.feature.findroute.presenter.ui.findroute.viewmodel.FindRouteIntent
import com.composetest.feature.findroute.presenter.ui.findroute.viewmodel.FindRouteIntentReceiver
import com.composetest.feature.findroute.presenter.ui.findroute.viewmodel.FindRouteUiEvent
import com.composetest.feature.findroute.presenter.ui.findroute.viewmodel.FindRouteUiState
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
internal fun NewsListScreen(
    uiState: FindRouteUiState,
    uiEvent: Flow<FindRouteUiEvent> = emptyFlow(),
    onExecuteIntent: (Intent<FindRouteIntentReceiver>) -> Unit = {},
    navController: NavHostController = rememberNavController(),
) {
    val permissionState = getMultiplePermissionState(Permission.localization)
    val patosDeMinas = LatLng(-18.587, -46.514)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(patosDeMinas, 12f) // Zoom level 12f
    }
    val markerState = remember {
        MarkerState(position = patosDeMinas)
    }
    UiEventsHandler(uiEvent = uiEvent, navController = navController)
    LifecycleEventHandler(onExecuteIntent = onExecuteIntent)
    Box(modifier = Modifier.fillMaxSize()) {
        if (uiState.showFullScreenMsg) {
            FullScreenMessage(
                uiState = uiState,
                onExecuteIntent = onExecuteIntent,
                permissionState = permissionState
            )
            return
        }
        GoogleMap(
            modifier = Modifier.matchParentSize(),
            cameraPositionState = cameraPositionState
        ) {
            Marker(
                state = markerState,
                title = "Patos de Minas",
                snippet = "State of Minas Gerais, Brazil"
            )
        }
        BackButton(
            modifier = Modifier
                .align(Alignment.TopStart)
                .statusBarsPadding()
        )
    }
}

@Composable
private fun FullScreenMessage(
    uiState: FindRouteUiState,
    onExecuteIntent: (Intent<FindRouteIntentReceiver>) -> Unit,
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
//                    onExecuteIntent(WeatherForecastIntent.GetLocationAndWeatherForecastsData)
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
//    val buttonText = if (permissionState.shouldShowRationale) {
//        R.string.
//    } else {
//        WeatherForecastResources.string.weather_forecast_active_permissions_blocked
//    }
    Button(text = "teste") {
        if (permissionState.shouldShowRationale) {
            permissionState.launchMultiplePermissionRequest()
        } else {
            context.navigateToApplicationDetailSettings()
        }
    }
}

@Composable
private fun UiEventsHandler(
    uiEvent: Flow<FindRouteUiEvent>,
    navController: NavHostController
) {
    UiEventsObserver(uiEvent) {

    }
}

@Composable
private fun LifecycleEventHandler(
    onExecuteIntent: (Intent<FindRouteIntentReceiver>) -> Unit
) {
    LifecycleEvent(onResume = {
        onExecuteIntent(FindRouteIntent.OnResume)
    })
}

@Preview
@Composable
private fun Preview() {
    ComposeTestTheme {
        NewsListScreen(
            uiState = FindRouteUiState(),
        )
    }
}