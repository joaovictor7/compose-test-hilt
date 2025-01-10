package com.composetest.feature.configuration.ui.security

import com.composetest.core.domain.managers.ConfigurationManager
import com.composetest.core.domain.usecases.SendAnalyticsUseCase
import com.composetest.core.router.di.qualifiers.NavGraphQualifier
import com.composetest.core.router.enums.NavGraph
import com.composetest.core.router.managers.NavigationManager
import com.composetest.core.ui.bases.BaseViewModel
import com.composetest.feature.configuration.analytics.theme.ConfigurationThemeScreenAnalytic
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class ConfigurationSecurityViewModel @Inject constructor(
    private val configurationManager: ConfigurationManager,
    override val sendAnalyticsUseCase: SendAnalyticsUseCase,
    @NavGraphQualifier(NavGraph.MAIN) override val navigationManager: NavigationManager
) : BaseViewModel<ConfigurationSecurityUiState, ConfigurationSecurityUiEvent>(
    ConfigurationThemeScreenAnalytic,
    ConfigurationSecurityUiState()
), ConfigurationSecurityCommandReceiver {

    private val currentConfiguration get() = configurationManager.currentConfiguration

    override val commandReceiver = this

    override fun initUiState() {
        runAsyncTask {
            configurationManager.fetchConfiguration()
            updateUiState {
                it.initUiState(currentConfiguration?.biometricLogin)
            }
        }
    }

    override fun changeSwitchBiometric(checked: Boolean) {
        runAsyncTask {
            configurationManager.setBiometricLogin(checked)
            updateUiState { it.copy(biometricIsEnabled = checked) }
        }
    }
}