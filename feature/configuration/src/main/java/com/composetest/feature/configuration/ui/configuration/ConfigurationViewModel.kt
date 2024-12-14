package com.composetest.feature.configuration.ui.configuration

import com.composetest.core.domain.usecases.SendAnalyticsUseCase
import com.composetest.core.router.destinations.configuration.ConfigurationThemeDestination
import com.composetest.core.router.di.qualifiers.NavGraphQualifier
import com.composetest.core.router.enums.NavGraph
import com.composetest.core.router.managers.NavigationManager
import com.composetest.core.ui.bases.BaseViewModel
import com.composetest.feature.configuration.analytics.configuration.ConfigurationScreenAnalytic
import com.composetest.feature.configuration.enums.Configuration
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class ConfigurationViewModel @Inject constructor(
    override val sendAnalyticsUseCase: SendAnalyticsUseCase,
    @NavGraphQualifier(NavGraph.MAIN) override val navigationManager: NavigationManager
) : BaseViewModel<ConfigurationUiState, ConfigurationUiEvent>(
    ConfigurationScreenAnalytic,
    ConfigurationUiState()
), ConfigurationCommandReceiver {

    override val commandReceiver = this

    override fun initUiState() {
        openScreenAnalytic()
        updateUiState { it.setConfigurations(Configuration.entries) }
    }

    override fun clickConfiguration(configuration: Configuration) {
        navigationManager.navigate(ConfigurationThemeDestination)
    }
}