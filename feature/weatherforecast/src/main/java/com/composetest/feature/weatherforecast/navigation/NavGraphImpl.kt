package com.composetest.feature.weatherforecast.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.composetest.core.router.interfaces.NavGraph
import com.composetest.core.router.navkey.weatherforecast.WeatherForecastNavKey
import com.composetest.feature.weatherforecast.presenter.ui.WeatherForecastScreen
import com.composetest.feature.weatherforecast.presenter.ui.viewmodel.WeatherForecastViewModel
import javax.inject.Inject

internal class NavGraphImpl @Inject constructor() : NavGraph {
    override fun EntryProviderScope<NavKey>.registerEntries(navBackStack: NavBackStack<NavKey>) {
        entry<WeatherForecastNavKey> { _ ->
            val viewModel = hiltViewModel<WeatherForecastViewModel>()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            WeatherForecastScreen(
                uiState = uiState,
                uiEvent = viewModel.uiEvent,
                onExecuteIntent = viewModel::executeIntent,
                navBackStack = navBackStack,
            )
        }
    }
}
