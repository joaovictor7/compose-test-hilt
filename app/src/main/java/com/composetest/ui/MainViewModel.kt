package com.composetest.ui

import com.composetest.core.domain.managers.AppThemeManager
import com.composetest.core.domain.managers.SessionManager
import com.composetest.core.domain.usecases.AnalyticsUseCase
import com.composetest.core.router.destinations.login.LoginDestination
import com.composetest.core.router.enums.NavigationMode
import com.composetest.core.router.managers.NavigationManager
import com.composetest.core.router.providers.NavHostControllerProvider
import com.composetest.core.ui.bases.BaseViewModel
import com.composetest.ui.analytics.MainAnalytic
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val appThemeManager: AppThemeManager,
    private val sessionManager: SessionManager,
    private val navigationManager: NavigationManager,
    private val navHostControllerProvider: NavHostControllerProvider,
    override val analyticsUseCase: AnalyticsUseCase
) : BaseViewModel<MainUiState>(MainAnalytic, MainUiState()), MainCommandReceiver {

    override val commandReceiver = this

    init {
        iniState()
        getInitialData()
    }

    override fun verifySession() {
        runAsyncTask {
            val validSession = sessionManager.isSessionValid()
            val currentScreenIsLogin = navHostControllerProvider.isCurrentScreen(LoginDestination)
            if (!validSession && !currentScreenIsLogin) {
                navigationManager.navigate(
                    LoginDestination,
                    NavigationMode.REMOVE_ALL_SCREENS_STACK
                )
            }
        }
    }

    private fun iniState() {
        runFlowTask(flow = appThemeManager.getAppTheme()) { appTheme ->
            updateUiState { it.setAppTheme(appTheme) }
        }
    }

    private fun getInitialData() {
        updateUiState { it.splashScreenFinished() }
    }
}