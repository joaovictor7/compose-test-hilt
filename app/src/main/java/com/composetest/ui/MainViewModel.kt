package com.composetest.ui

import androidx.navigation.NavHostController
import com.composetest.R
import com.composetest.core.designsystem.params.alertdialogs.ButtonAlertDialogParam
import com.composetest.core.designsystem.params.alertdialogs.DefaultAlertDialogParam
import com.composetest.core.domain.managers.AppThemeManager
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
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.composetest.core.designsystem.R as DesignSystemResources

@HiltViewModel
internal class MainViewModel @Inject constructor(
    private val appThemeManager: AppThemeManager,
    private val sessionManager: SessionManager,
    private val navControllerManager: NavControllerManager,
    private val remoteConfigManager: RemoteConfigManager,
    override val sendAnalyticsUseCase: SendAnalyticsUseCase,
    @NavGraphQualifier(NavGraph.MAIN) override val navigationManager: NavigationManager
) : BaseViewModel<MainUiState, MainUiEvent>(MainAnalytic, MainUiState()), MainCommandReceiver {

    override val commandReceiver = this

    init {
        appThemeObservable()
    }

    override fun initUiState() {
        updateUiState { it.splashScreenFinished() }
    }

    override fun verifySession() {
        with(navigationManager) {
            runAsyncTask {
                val validSession = sessionManager.isSessionValid()
                if (!validSession && currentRoute != LoginDestination.asRoute) {
                    showAlertDialogSession()
                    navigationManager.navigate(
                        LoginDestination,
                        NavigationMode.REMOVE_ALL_SCREENS_STACK
                    )
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

    private fun appThemeObservable() {
        runFlowTask(flow = appThemeManager.appThemeFlow) { appTheme ->
            updateUiState { it.setAppTheme(appTheme) }
        }
    }

    private fun showAlertDialogSession() {
        updateUiState { uiState ->
            uiState.setDefaultAlertDialogParam(
                DefaultAlertDialogParam(
                    iconId = DesignSystemResources.drawable.ic_person_off,
                    title = R.string.alert_dialog_session_invalid_title,
                    text = R.string.alert_dialog_session_invalid_text,
                    onDismiss = ButtonAlertDialogParam(
                        textId = DesignSystemResources.string.global_word_close,
                        onClick = { updateUiState { it.setDefaultAlertDialogParam(null) } }
                    )
                )
            )
        }
    }
}