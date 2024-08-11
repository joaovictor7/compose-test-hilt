package com.composetest.feature.configuration.ui.menu

import com.composetest.core.domain.usecases.SendAnalyticsUseCase
import com.composetest.core.ui.bases.BaseViewModel
import com.composetest.feature.configuration.ui.menu.analytics.ConfigurationMenuAnalytic
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class ConfigurationsMenuViewModel @Inject constructor(
    override val sendAnalyticsUseCase: SendAnalyticsUseCase
) : BaseViewModel<ConfigurationsMenuUiState>(
    ConfigurationMenuAnalytic,
    ConfigurationsMenuUiState()
), ConfigurationsMenuCommandReceiver {

    override val commandReceiver = this

}