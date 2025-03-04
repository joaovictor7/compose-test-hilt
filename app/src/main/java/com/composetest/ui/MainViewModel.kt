package com.composetest.ui

import com.composetest.analytics.MainAnalytic
import com.composetest.core.analytic.AnalyticSender
import com.composetest.core.domain.usecases.configuration.GetAppThemeUseCase
import com.composetest.core.domain.usecases.remoteconfigs.FetchRemoteConfigUseCase
import com.composetest.core.domain.usecases.session.CheckNeedsLoginUseCase
import com.composetest.core.domain.usecases.session.CheckSessionIsValidUseCase
import com.composetest.core.router.destinations.login.LoginDestination
import com.composetest.core.router.destinations.root.RootDestination
import com.composetest.core.router.enums.NavigationMode
import com.composetest.core.router.models.NavigationModel
import com.composetest.core.ui.bases.BaseViewModel
import com.composetest.core.ui.interfaces.UiEvent
import com.composetest.core.ui.interfaces.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
internal class MainViewModel @Inject constructor(
    private val checkSessionIsValidUseCase: CheckSessionIsValidUseCase,
    private val checkNeedsLoginUseCase: CheckNeedsLoginUseCase,
    private val getAppThemeUseCase: GetAppThemeUseCase,
    private val fetchRemoteConfigUseCase: FetchRemoteConfigUseCase,
    override val analyticSender: AnalyticSender,
) : BaseViewModel(), UiState<MainUiState>, UiEvent<MainUiEvent>, MainCommandReceiver {

    override val commandReceiver = this
    override val analyticScreen = MainAnalytic

    private val _uiState = MutableStateFlow(MainUiState())
    override val uiState = _uiState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<MainUiEvent>()
    override val uiEvent = _uiEvent.asSharedFlow()

    init {
        appThemeObservable()
        initUiState()
    }

    override fun verifySession(currentRoute: String?) {
        runAsyncTask {
            val validSession = checkSessionIsValidUseCase()
            val loginDestination = LoginDestination(expiredSession = true)
            if (!validSession && currentRoute != loginDestination.asRoute) {
                _uiEvent.emitEvent(
                    MainUiEvent.NavigateTo(
                        NavigationModel(loginDestination, NavigationMode.REMOVE_ALL_SCREENS_STACK)
                    )
                )
            }
        }
    }

    override fun fetchRemoteConfig() {
        fetchRemoteConfigUseCase()
    }

    override fun dismissAlertDialog() {
        _uiState.update { it.setSimpleDialog(null) }
    }

    private fun initUiState() = runAsyncTask {
        val firstDestination = if (checkNeedsLoginUseCase()) {
            LoginDestination()
        } else {
            RootDestination
        }
        _uiState.update { it.setInitUiState(firstDestination) }
    }

    private fun appThemeObservable() {
        runFlowTask(getAppThemeUseCase()) { appTheme ->
            _uiState.update { it.setAppTheme(appTheme) }
        }
    }
}