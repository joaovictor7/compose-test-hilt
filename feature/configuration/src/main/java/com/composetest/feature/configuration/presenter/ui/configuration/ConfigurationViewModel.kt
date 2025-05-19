package com.composetest.feature.configuration.presenter.ui.configuration

import androidx.lifecycle.viewModelScope
import com.composetest.core.analytic.AnalyticSender
import com.composetest.core.analytic.events.CommonAnalyticEvent
import com.composetest.core.router.models.NavigationModel
import com.composetest.core.ui.bases.BaseViewModel
import com.composetest.core.ui.di.qualifiers.AsyncTaskUtilsQualifier
import com.composetest.core.ui.interfaces.UiEvent
import com.composetest.core.ui.interfaces.UiState
import com.composetest.core.ui.utils.AsyncTaskUtils
import com.composetest.feature.configuration.analytic.screens.ConfigurationScreenAnalytic
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
    @AsyncTaskUtilsQualifier(ConfigurationScreenAnalytic.SCREEN) private val asyncTaskUtils: AsyncTaskUtils,
) : BaseViewModel(), UiState<ConfigurationUiState>, UiEvent<ConfigurationUiEvent>,
    ConfigurationCommandReceiver {

    override val commandReceiver = this

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