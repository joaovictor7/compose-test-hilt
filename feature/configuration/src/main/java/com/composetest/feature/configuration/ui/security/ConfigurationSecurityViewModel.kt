package com.composetest.feature.configuration.ui.security

import com.composetest.core.domain.usecases.SendAnalyticsUseCase
import com.composetest.core.domain.usecases.SetUseBiometricUseCase
import com.composetest.core.router.di.qualifiers.NavGraphQualifier
import com.composetest.core.router.enums.NavGraph
import com.composetest.core.router.managers.NavigationManager
import com.composetest.core.ui.bases.BaseViewModel
import com.composetest.feature.configuration.analytics.theme.ConfigurationThemeScreenAnalytic
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class ConfigurationSecurityViewModel @Inject constructor(
    private val setUseBiometricUseCase: SetUseBiometricUseCase,
    override val sendAnalyticsUseCase: SendAnalyticsUseCase,
    @NavGraphQualifier(NavGraph.MAIN) override val navigationManager: NavigationManager
) : BaseViewModel<ConfigurationSecurityUiState, ConfigurationSecurityUiEvent>(
    ConfigurationThemeScreenAnalytic,
    ConfigurationSecurityUiState()
), ConfigurationSecurityCommandReceiver {

    override val commandReceiver = this

    override fun initUiState() {

    }

    override fun changeSwitchBiometric(checked: Boolean) {
        runAsyncTask {
            setUseBiometricUseCase(checked)
        }
    }
}