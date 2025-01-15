package com.composetest.ui

import androidx.navigation.NavHostController
import com.composetest.core.domain.managers.ConfigurationManager
import com.composetest.core.domain.managers.RemoteConfigManager
import com.composetest.core.domain.managers.SessionManager
import com.composetest.core.domain.usecases.SendAnalyticsUseCase
import com.composetest.core.router.destinations.login.LoginDestination
import com.composetest.core.router.di.qualifiers.NavGraphQualifier
import com.composetest.core.router.enums.NavGraph
import com.composetest.core.router.enums.NavigationMode
import com.composetest.core.router.managers.NavControllerManager
import com.composetest.core.router.managers.NavigationManager
import com.composetest.core.ui.bases.BaseViewModel
import com.composetest.ui.analytics.MainAnalytic
import com.composetest.ui.dialogs.SimpleDialogParam
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class MainViewModel @Inject constructor(
    private val configurationManager: ConfigurationManager,
    private val sessionManager: SessionManager,
    private val navControllerManager: NavControllerManager,
    private val remoteConfigManager: RemoteConfigManager,
    override val sendAnalyticsUseCase: SendAnalyticsUseCase,
    @NavGraphQualifier(NavGraph.MAIN) override val navigationManager: NavigationManager
) : BaseViewModel<MainUiState, MainUiEvent>(MainUiState()), MainCommandReceiver {

    override val commandReceiver = this
    override val analyticScreen = MainAnalytic

    init {
        themeObservable()
    }

    override fun initUiState() {
        updateUiState { it.splashScreenFinished() }
    }

    override fun verifySession() {
        with(navigationManager) {
            runAsyncTask {
                val validSession = sessionManager.sessionIsLogged()
                val loginDestination = LoginDestination()
                if (!validSession && currentRoute != loginDestination.asRoute) {
                    showAlertDialogSession()
                    navigate(loginDestination, NavigationMode.REMOVE_ALL_SCREENS_STACK)
                }
            }
        }
    }

    override fun setMainNavGraph(navController: NavHostController) {
        navControllerManager.setNavController(NavGraph.MAIN, navController)
    }

    override fun fetchRemoteConfig() {
        remoteConfigManager.fetch()
    }

    override fun dismissAlertDialog() {
        updateUiState { it.setSimpleDialog(null) }
    }

    private fun themeObservable() {
        runFlowTask(configurationManager.theme) { theme ->
            updateUiState { it.setTheme(theme) }
        }
    }

    private fun showAlertDialogSession() {
        updateUiState { uiState ->
            uiState.setSimpleDialog(SimpleDialogParam.ExpiredSession)
        }
    }
}