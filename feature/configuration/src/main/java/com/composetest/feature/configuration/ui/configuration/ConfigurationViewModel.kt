package com.composetest.feature.configuration.ui.configuration

import com.composetest.core.domain.usecases.SendAnalyticsUseCase
import com.composetest.core.router.destinations.configuration.ConfigurationThemeDestination
import com.composetest.core.router.di.qualifiers.NavGraphQualifier
import com.composetest.core.router.enums.NavGraph
import com.composetest.core.router.managers.NavigationManager
import com.composetest.core.ui.bases.BaseViewModel
import com.composetest.feature.configuration.enums.Configuration
import com.composetest.feature.configuration.ui.configuration.analytics.ConfigurationAnalytic
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class ConfigurationViewModel @Inject constructor(
    @NavGraphQualifier(NavGraph.MAIN) private val navigationManager: NavigationManager,
    override val sendAnalyticsUseCase: SendAnalyticsUseCase
) : BaseViewModel<ConfigurationUiState>(
    ConfigurationAnalytic,
    ConfigurationUiState()
), ConfigurationCommandReceiver {

    override val commandReceiver = this

    init {
        initState()
    }

    override fun clickConfiguration(configuration: Configuration) {
        navigationManager.navigate(ConfigurationThemeDestination)
    }

    private fun initState() {
        updateUiState { it.setConfigurations(Configuration.entries) }
    }
}