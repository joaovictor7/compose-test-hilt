package com.composetest.feature.configuration.presenter.ui.configuration

import androidx.lifecycle.viewModelScope
import com.composetest.core.analytic.event.CommonAnalyticEvent
import com.composetest.core.analytic.sender.AnalyticSender
import com.composetest.core.router.model.NavigationModel
import com.composetest.core.ui.base.BaseViewModel
import com.composetest.core.ui.di.qualifier.AsyncTaskUtilsQualifier
import com.composetest.core.ui.interfaces.UiEvent
import com.composetest.core.ui.interfaces.UiState
import com.composetest.core.ui.util.AsyncTaskUtils
import com.composetest.feature.configuration.analytic.screen.ConfigurationScreenAnalytic
import com.composetest.feature.configuration.presenter.enums.Configuration
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
internal class ConfigurationViewModel @Inject constructor(
    private val analyticSender: AnalyticSender,
    @param:AsyncTaskUtilsQualifier(ConfigurationScreenAnalytic.SCREEN) private val asyncTaskUtils: AsyncTaskUtils,
) : BaseViewModel(), UiState<ConfigurationUiState>, UiEvent<ConfigurationUiEvent>,
    ConfigurationIntentReceiver {

    override val intentReceiver = this

    private val _uiState = MutableStateFlow(ConfigurationUiState())
    override val uiState = _uiState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<ConfigurationUiEvent>()
    override val uiEvent = _uiEvent.asSharedFlow()

    init {
        sendOpenScreenAnalytic()
        initUiState()
    }

    override fun sendOpenScreenAnalytic() {
        asyncTaskUtils.runAsyncTask(viewModelScope) {
            analyticSender.sendEvent(CommonAnalyticEvent.OpenScreen(ConfigurationScreenAnalytic))
        }
    }

    override fun configurationClick(configuration: Configuration) {
        _uiEvent.emitEvent(ConfigurationUiEvent.NavigateTo(NavigationModel(configuration.destination)))
    }

    private fun initUiState() {
        _uiState.update { it.setConfigurations(Configuration.entries) }
    }
}