package com.composetest.feature.configuration.ui.configuration

import com.composetest.core.domain.usecases.SendAnalyticsUseCase
import com.composetest.core.ui.bases.BaseViewModel
import com.composetest.feature.configuration.ui.configuration.analytics.ConfigurationMenuAnalytic
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class ConfigurationMenuViewModel @Inject constructor(
    override val sendAnalyticsUseCase: SendAnalyticsUseCase
) : BaseViewModel<ConfigurationMenuUiState>(
    ConfigurationMenuAnalytic,
    ConfigurationMenuUiState()
), ConfigurationMenuCommandReceiver {

    override val commandReceiver = this

}