package com.composetest.feature.configuration.navigation

import androidx.navigation.NavGraphBuilder
import com.composetest.core.router.destinations.configuration.ConfigurationDestination
import com.composetest.core.router.destinations.configuration.ConfigurationThemeDestination
import com.composetest.core.ui.extensions.buildComposable
import com.composetest.feature.configuration.ui.configuration.ConfigurationCommandReceiver
import com.composetest.feature.configuration.ui.configuration.ConfigurationScreen
import com.composetest.feature.configuration.ui.configuration.ConfigurationUiEvent
import com.composetest.feature.configuration.ui.configuration.ConfigurationUiState
import com.composetest.feature.configuration.ui.configuration.ConfigurationViewModel
import com.composetest.feature.configuration.ui.theme.ConfigurationThemeCommandReceiver
import com.composetest.feature.configuration.ui.theme.ConfigurationThemeScreen
import com.composetest.feature.configuration.ui.theme.ConfigurationThemeUiEvent
import com.composetest.feature.configuration.ui.theme.ConfigurationThemeUiState
import com.composetest.feature.configuration.ui.theme.ConfigurationThemeViewModel

fun NavGraphBuilder.configurationNavGraph() {
    buildComposable<ConfigurationDestination, ConfigurationViewModel, ConfigurationUiState, ConfigurationUiEvent, ConfigurationCommandReceiver>(
        screen = ConfigurationScreen,
        navigateBackHandler = false
    )
    buildComposable<ConfigurationThemeDestination, ConfigurationThemeViewModel, ConfigurationThemeUiState, ConfigurationThemeUiEvent, ConfigurationThemeCommandReceiver>(
        screen = ConfigurationThemeScreen
    )
}