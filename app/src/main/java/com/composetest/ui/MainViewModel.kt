package com.composetest.ui

import com.composetest.core.domain.managers.ConfigurationManager
import com.composetest.core.domain.managers.RemoteConfigManager
import com.composetest.core.domain.managers.SessionManager
import com.composetest.core.domain.usecases.SendAnalyticsUseCase
import com.composetest.core.router.destinations.login.LoginDestination
import com.composetest.core.router.enums.NavigationMode
import com.composetest.core.router.models.NavigationModel
import com.composetest.core.ui.bases.BaseViewModel
import com.composetest.core.ui.interfaces.UiEvent
import com.composetest.core.ui.interfaces.UiState
import com.composetest.ui.analytics.MainAnalytic
import com.composetest.ui.dialogs.SimpleDialogParam
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
internal class MainViewModel @Inject constructor(
    private val configurationManager: ConfigurationManager,
    private val sessionManager: SessionManager,
    private val remoteConfigManager: RemoteConfigManager,
    override val sendAnalyticsUseCase: SendAnalyticsUseCase,
) : BaseViewModel(), UiState<MainUiState>, UiEvent<MainUiEvent>, MainCommandReceiver {

    private val _uiState = MutableStateFlow(MainUiState())
    private val _uiEvent = MutableSharedFlow<MainUiEvent>()

    override val uiState = _uiState.asStateFlow()
    override val uiEvent = _uiEvent.asSharedFlow()
    override val commandReceiver = this
    override val analyticScreen = MainAnalytic

    init {
        themeObservable()
        initUiState()
    }

    override fun verifySession(currentRoute: String?) {
        runAsyncTask {
            val validSession = sessionManager.sessionIsLogged()
            val loginDestination = LoginDestination()
            if (!validSession && currentRoute != loginDestination.asRoute) {
                showAlertDialogSession()
                _uiEvent.emitEvent(
                    MainUiEvent.NavigateTo(
                        NavigationModel(loginDestination, NavigationMode.REMOVE_ALL_SCREENS_STACK)
                    )
                )
            }
        }
    }

    override fun fetchRemoteConfig() {
        remoteConfigManager.fetch()
    }

    override fun dismissAlertDialog() {
        _uiState.update { it.setSimpleDialog(null) }
    }

    private fun initUiState() {
        _uiState.update { it.splashScreenFinished() }
    }

    private fun themeObservable() {
        runFlowTask(configurationManager.theme) { theme ->
            _uiState.update { it.setTheme(theme) }
        }
    }

    private fun showAlertDialogSession() {
        _uiState.update { uiState ->
            uiState.setSimpleDialog(SimpleDialogParam.ExpiredSession)
        }
    }
}