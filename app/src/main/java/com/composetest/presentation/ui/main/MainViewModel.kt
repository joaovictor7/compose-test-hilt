package com.composetest.presentation.ui.main

import androidx.lifecycle.viewModelScope
import com.composetest.analytic.screen.MainScreenAnalytic
import com.composetest.core.domain.usecase.remoteconfig.FetchRemoteConfigUseCase
import com.composetest.core.domain.usecase.session.CheckSessionIsValidUseCase
import com.composetest.core.router.destination.login.LoginDestination
import com.composetest.core.router.destination.root.RootDestination
import com.composetest.core.router.enums.NavigationMode
import com.composetest.core.router.interfaces.NavGraph
import com.composetest.core.router.model.NavigationModel
import com.composetest.core.ui.base.BaseViewModel
import com.composetest.core.ui.di.qualifier.AsyncTaskUtilsQualifier
import com.composetest.core.ui.interfaces.UiEvent
import com.composetest.core.ui.interfaces.UiState
import com.composetest.core.ui.util.AsyncTaskUtils
import com.composetest.domain.usecase.CheckNeedsLoginUseCase
import com.composetest.domain.usecase.GetAppThemeUseCase
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
    private val navGraphs: Array<NavGraph>,
    @param:AsyncTaskUtilsQualifier(MainScreenAnalytic.SCREEN) private val asyncTaskUtils: AsyncTaskUtils,
) : BaseViewModel(), UiState<MainUiState>, UiEvent<MainUiEvent>, MainIntentReceiver {

    override val intentReceiver = this

    private val _uiState = MutableStateFlow(MainUiState())
    override val uiState = _uiState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<MainUiEvent>()
    override val uiEvent = _uiEvent.asSharedFlow()

    init {
        appThemeObservable()
        initUiState()
    }

    override fun verifySession(currentRoute: String?) {
        asyncTaskUtils.runAsyncTask(viewModelScope) {
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

    private fun initUiState() = asyncTaskUtils.runAsyncTask(viewModelScope) {
        val firstDestination = if (checkNeedsLoginUseCase()) {
            LoginDestination()
        } else {
            RootDestination
        }
        _uiState.update { it.setInitUiState(firstDestination, navGraphs) }
    }

    private fun appThemeObservable() {
        asyncTaskUtils.runFlowTask(
            coroutineScope = viewModelScope,
            flow = getAppThemeUseCase()
        ) { appTheme ->
            _uiState.update { it.setAppTheme(appTheme) }
        }
    }
}