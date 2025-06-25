package com.composetest.feature.configuration.presenter.ui.security

import androidx.lifecycle.viewModelScope
import com.composetest.core.analytic.event.CommonAnalyticEvent
import com.composetest.core.analytic.sender.AnalyticSender
import com.composetest.core.security.provider.BiometricProvider
import com.composetest.core.ui.base.BaseViewModel
import com.composetest.core.ui.di.qualifier.AsyncTaskUtilsQualifier
import com.composetest.core.ui.interfaces.UiState
import com.composetest.core.ui.util.AsyncTaskUtils
import com.composetest.feature.configuration.analytic.screen.SecurityConfigurationScreenAnalytic
import com.composetest.feature.configuration.domain.model.SecurityConfigurationModel
import com.composetest.feature.configuration.domain.usecase.GetSecurityConfigurationUseCase
import com.composetest.feature.configuration.domain.usecase.UpdateSecurityConfigurationUseCase
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
    @param:AsyncTaskUtilsQualifier(SecurityConfigurationScreenAnalytic.SCREEN) private val asyncTaskUtils: AsyncTaskUtils,
) : BaseViewModel(), UiState<SecurityConfigurationUiState>, SecurityConfigurationIntentReceiver {

    override val intentReceiver = this

    private var securityConfiguration: SecurityConfigurationModel? = null

    private val _uiState = MutableStateFlow(SecurityConfigurationUiState())
    override val uiState = _uiState.asStateFlow()

    init {
        sendOpenScreenAnalytic()
        initUiState()
    }

    override fun sendOpenScreenAnalytic() {
        asyncTaskUtils.runAsyncTask(viewModelScope) {
            analyticSender.sendEvent(
                CommonAnalyticEvent.OpenScreen(SecurityConfigurationScreenAnalytic)
            )
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