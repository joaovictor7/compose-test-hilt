package com.composetest.feature.configuration.ui.security

import com.composetest.core.domain.models.configuration.SecurityConfigurationModel
import com.composetest.core.domain.usecases.SendAnalyticsUseCase
import com.composetest.core.domain.usecases.configuration.GetSecurityConfigurationUseCase
import com.composetest.core.domain.usecases.configuration.UpdateSecurityConfigurationUseCase
import com.composetest.core.security.providers.BiometricProvider
import com.composetest.core.ui.bases.BaseViewModel
import com.composetest.core.ui.interfaces.UiState
import com.composetest.feature.configuration.analytics.theme.ConfigurationThemeScreenAnalytic
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
internal class ConfigurationSecurityViewModel @Inject constructor(
    private val getSecurityConfigurationUseCase: GetSecurityConfigurationUseCase,
    private val updateSecurityConfigurationUseCase: UpdateSecurityConfigurationUseCase,
    private val biometricProvider: BiometricProvider,
    override val sendAnalyticsUseCase: SendAnalyticsUseCase,
) : BaseViewModel(), UiState<ConfigurationSecurityUiState>, ConfigurationSecurityCommandReceiver {

    private val _uiState = MutableStateFlow(ConfigurationSecurityUiState())
    private var securityConfiguration: SecurityConfigurationModel? = null

    override val uiState = _uiState.asStateFlow()
    override val commandReceiver = this
    override val analyticScreen = ConfigurationThemeScreenAnalytic

    init {
        initUiState()
    }

    override fun changeSwitchBiometric(checked: Boolean) {
        runAsyncTask {
            updateSecurityConfigurationUseCase(securityConfiguration?.apply {
                biometricLogin = checked
            })
            _uiState.update { it.copy(biometricIsEnabled = checked) }
        }
    }

    private fun initUiState() {
        val biometricIsAvailable = biometricProvider.biometricIsAvailable
        runAsyncTask {
            securityConfiguration = getSecurityConfigurationUseCase()
            _uiState.update {
                it.initUiState(biometricIsAvailable, securityConfiguration?.biometricLogin)
            }
        }
    }
}