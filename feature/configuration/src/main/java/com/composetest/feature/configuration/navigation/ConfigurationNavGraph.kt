package com.composetest.feature.configuration.navigation

import androidx.navigation.NavGraphBuilder
import com.composetest.core.router.destinations.configuration.ConfigurationSecurityDestination
import com.composetest.core.router.destinations.configuration.ConfigurationThemeDestination
import com.composetest.core.router.interfaces.NavGraph
import com.composetest.core.ui.extensions.buildComposable
import com.composetest.feature.configuration.ui.security.ConfigurationSecurityCommandReceiver
import com.composetest.feature.configuration.ui.security.ConfigurationSecurityScreen
import com.composetest.feature.configuration.ui.security.ConfigurationSecurityUiEvent
import com.composetest.feature.configuration.ui.security.ConfigurationSecurityUiState
import com.composetest.feature.configuration.ui.security.ConfigurationSecurityViewModel
import com.composetest.feature.configuration.ui.theme.ConfigurationThemeCommandReceiver
import com.composetest.feature.configuration.ui.theme.ConfigurationThemeScreen
import com.composetest.feature.configuration.ui.theme.ConfigurationThemeUiEvent
import com.composetest.feature.configuration.ui.theme.ConfigurationThemeUiState
import com.composetest.feature.configuration.ui.theme.ConfigurationThemeViewModel

object ConfigurationNavGraph : NavGraph {
    override fun NavGraphBuilder.navGraph(navigateBackHandler: Boolean) {
        buildComposable<ConfigurationThemeDestination, ConfigurationThemeViewModel, ConfigurationThemeUiState, ConfigurationThemeUiEvent, ConfigurationThemeCommandReceiver>(
            screen = ConfigurationThemeScreen,
            navigateBackHandler = navigateBackHandler,
        )
        buildComposable<ConfigurationSecurityDestination, ConfigurationSecurityViewModel, ConfigurationSecurityUiState, ConfigurationSecurityUiEvent, ConfigurationSecurityCommandReceiver>(
            screen = ConfigurationSecurityScreen,
            navigateBackHandler = navigateBackHandler,
        )
    }
}