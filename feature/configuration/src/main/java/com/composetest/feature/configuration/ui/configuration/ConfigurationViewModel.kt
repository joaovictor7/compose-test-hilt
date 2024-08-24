package com.composetest.feature.configuration.ui.configuration

import com.composetest.core.domain.usecases.SendAnalyticsUseCase
import com.composetest.core.ui.bases.BaseViewModel
import com.composetest.feature.configuration.enums.Configuration
import com.composetest.feature.configuration.ui.configuration.analytics.ConfigurationMenuAnalytic
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class ConfigurationViewModel @Inject constructor(
    override val sendAnalyticsUseCase: SendAnalyticsUseCase
) : BaseViewModel<ConfigurationUiState>(
    ConfigurationMenuAnalytic,
    ConfigurationUiState()
), ConfigurationCommandReceiver {

    override val commandReceiver = this

    init {
        initState()
    }

    override fun clickConfiguration(configuration: Configuration) {
        // TODO: implement
    }

    private fun initState() {
        updateUiState { it.setConfigurations(Configuration.entries) }
    }
}