package com.composetest.feature.configuration.ui.security

import androidx.lifecycle.viewModelScope
import com.composetest.core.analytic.AnalyticSender
import com.composetest.core.analytic.enums.ScreensAnalytic
import com.composetest.core.analytic.events.CommonAnalyticEvent
import com.composetest.core.domain.models.configuration.SecurityConfigurationModel
import com.composetest.core.domain.usecases.configuration.GetSecurityConfigurationUseCase
import com.composetest.core.domain.usecases.configuration.UpdateSecurityConfigurationUseCase
import com.composetest.core.security.providers.BiometricProvider
import com.composetest.core.ui.bases.BaseViewModel
import com.composetest.core.ui.di.qualifiers.AsyncTaskUtilsQualifier
import com.composetest.core.ui.interfaces.UiState
import com.composetest.core.ui.utils.AsyncTaskUtils
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
    private val analyticSender: AnalyticSender,
    @AsyncTaskUtilsQualifier(ScreensAnalytic.SECURITY_CONFIGURATION) private val asyncTaskUtils: AsyncTaskUtils,
) : BaseViewModel(), UiState<SecurityConfigurationUiState>, SecurityConfigurationCommandReceiver {

    override val commandReceiver = this

    private var securityConfiguration: SecurityConfigurationModel? = null

    private val _uiState = MutableStateFlow(SecurityConfigurationUiState())
    override val uiState = _uiState.asStateFlow()

    init {
        sendOpenScreenAnalytic()
        initUiState()
    }

    override fun sendOpenScreenAnalytic() {
        asyncTaskUtils.runAsyncTask(viewModelScope) {
            analyticSender.sendEvent(CommonAnalyticEvent.OpenScreen(ScreensAnalytic.SECURITY_CONFIGURATION))
        }
    }

    override fun changeSwitchBiometric(checked: Boolean) {
        asyncTaskUtils.runAsyncTask(viewModelScope) {
            updateSecurityConfigurationUseCase(securityConfiguration?.apply {
                biometricLogin = checked
            })
            _uiState.update { it.copy(biometricIsEnabled = checked) }
        }
    }

    private fun initUiState() {
        val biometricIsAvailable = biometricProvider.biometricIsAvailable
        asyncTaskUtils.runAsyncTask(viewModelScope) {
            securityConfiguration = getSecurityConfigurationUseCase()
            _uiState.update {
                it.initUiState(biometricIsAvailable, securityConfiguration?.biometricLogin)
            }
        }
    }
}