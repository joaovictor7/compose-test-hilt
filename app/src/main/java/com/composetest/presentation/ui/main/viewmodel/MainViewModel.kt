package com.composetest.presentation.ui.main.viewmodel

import androidx.lifecycle.viewModelScope
import androidx.navigation3.runtime.NavKey
import com.composetest.analytic.screen.MainScreenAnalytic
import com.composetest.core.domain.usecase.remoteconfig.FetchRemoteConfigUseCase
import com.composetest.core.domain.usecase.session.CheckSessionIsValidUseCase
import com.composetest.core.router.enums.NavigationMode
import com.composetest.core.router.interfaces.NavGraph
import com.composetest.core.router.model.NavigationModel
import com.composetest.core.router.navkey.login.LoginNavKey
import com.composetest.core.router.navkey.root.RootNavKey
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
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
internal class MainViewModel @Inject constructor(
    private val checkSessionIsValidUseCase: CheckSessionIsValidUseCase,
    private val checkNeedsLoginUseCase: CheckNeedsLoginUseCase,
    private val getAppThemeUseCase: GetAppThemeUseCase,
    private val fetchRemoteConfigUseCase: FetchRemoteConfigUseCase,
    private val navGraphs: Array<NavGraph>,
    private val coroutineContext: CoroutineContext,
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

    override fun verifySession(currentRoute: NavKey?) {
        viewModelScope.launch(coroutineContext) {
            asyncTaskUtils.runAsyncTask {
                val validSession = checkSessionIsValidUseCase()
                val loginNavKey = LoginNavKey(expiredSession = true)
                if (!validSession && currentRoute != loginNavKey) {
                    _uiEvent.emitEvent(
                        MainUiEvent.NavigateTo(
                            NavigationModel(loginNavKey, NavigationMode.REMOVE_ALL_SCREENS_STACK)
                        )
                    )
                }
            }
        }
    }

    override fun fetchRemoteConfig() {
        fetchRemoteConfigUseCase()
    }

    private fun initUiState() {
        viewModelScope.launch(coroutineContext) {
            asyncTaskUtils.runAsyncTask {
                val firstNavKey = if (checkNeedsLoginUseCase()) {
                    LoginNavKey()
                } else {
                    RootNavKey
                }
                _uiState.update { it.setInitUiState(firstNavKey, navGraphs) }
            }
        }
    }

    private fun appThemeObservable() {
        viewModelScope.launch(coroutineContext) {
            asyncTaskUtils.runFlowTask(
                flow = getAppThemeUseCase()
            ) { appTheme ->
                _uiState.update { it.setAppTheme(appTheme) }
            }
        }
    }
}