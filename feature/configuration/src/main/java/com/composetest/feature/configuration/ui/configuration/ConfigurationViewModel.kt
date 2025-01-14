package com.composetest.feature.configuration.ui.configuration

import androidx.lifecycle.viewModelScope
import com.composetest.core.domain.usecases.SendAnalyticsUseCase
import com.composetest.core.router.di.qualifiers.NavGraphQualifier
import com.composetest.core.router.enums.NavGraph
import com.composetest.core.router.managers.NavigationManager
import com.composetest.core.ui.bases.BaseViewModel
import com.composetest.feature.configuration.analytics.configuration.ConfigurationScreenAnalytic
import com.composetest.feature.configuration.enums.Configuration
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class ConfigurationViewModel @Inject constructor(
    private val sendAnalyticsUseCase: SendAnalyticsUseCase,
    @NavGraphQualifier(NavGraph.MAIN) override val navigationManager: NavigationManager
) : BaseViewModel<ConfigurationUiState, ConfigurationUiEvent>(ConfigurationUiState()),
    ConfigurationCommandReceiver {

    override val commandReceiver = this

    override fun initUiState() {
        viewModelScope.launch {
            
        }
        updateUiState { it.setConfigurations(Configuration.entries) }
    }

    override fun configurationClick(configuration: Configuration) {
        navigationManager.navigate(configuration.destination)
    }
}