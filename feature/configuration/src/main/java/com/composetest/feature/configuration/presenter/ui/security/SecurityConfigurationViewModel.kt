package com.composetest.feature.configuration.presenter.ui.security

import com.composetest.core.analytic.AnalyticSender
import com.composetest.core.analytic.events.configuration.SecurityConfigurationScreenAnalytic
import com.composetest.feature.configuration.domain.models.SecurityConfigurationModel
import com.composetest.feature.configuration.domain.usecases.GetSecurityConfigurationUseCase
import com.composetest.feature.configuration.domain.usecases.UpdateSecurityConfigurationUseCase
import com.composetest.core.security.providers.BiometricProvider
import com.composetest.core.ui.bases.BaseViewModel
import com.composetest.core.ui.interfaces.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
internal class SecurityConfigurationViewModel @Inject constructor(
    private val getSecurityConfigurationUseCase: GetSecurityConfigurationUseCase,
    private val updateSecurityConfigurationUseCase: UpdateSecurityConfigurationUseCase,
    private val biometricProvider: BiometricProvider,
    override val analyticSender: AnalyticSender,
) : BaseViewModel(), UiState<SecurityConfigurationUiState>, SecurityConfigurationCommandReceiver {

    override val commandReceiver = this
    override val analyticScreen = SecurityConfigurationScreenAnalytic

    private var securityConfiguration: SecurityConfigurationModel? = null

    private val _uiState = MutableStateFlow(SecurityConfigurationUiState())
    override val uiState = _uiState.asStateFlow()

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