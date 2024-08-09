package com.composetest.feature.login.ui.login

import com.composetest.common.providers.BuildConfigProvider
import com.composetest.core.domain.managers.AppThemeManager
import com.composetest.core.designsystem.components.alertdialogs.extensions.errorAlertDialogParam
import com.composetest.core.domain.enums.Theme
import com.composetest.core.domain.managers.SessionManager
import com.composetest.core.domain.models.AuthenticationCredentialsModel
import com.composetest.core.domain.throwables.InvalidCredentialsThrowable
import com.composetest.core.domain.usecases.AnalyticsUseCase
import com.composetest.core.domain.usecases.AuthenticationUseCase
import com.composetest.core.router.destinations.home.HomeDestination
import com.composetest.core.router.destinations.home.navtypes.InnerHome
import com.composetest.core.router.enums.NavigationMode
import com.composetest.core.router.managers.NavigationManager
import com.composetest.core.ui.bases.BaseViewModel
import com.composetest.feature.login.ui.login.analytics.LoginClickEventAnalytic
import com.composetest.feature.login.ui.login.analytics.LoginScreenAnalytic
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class LoginViewModel @Inject constructor(
    private val navigationManager: NavigationManager,
    private val buildConfigProvider: BuildConfigProvider,
    private val appThemeManager: AppThemeManager,
    private val authenticationUseCase: AuthenticationUseCase,
    private val sessionManager: SessionManager,
    override val analyticsUseCase: AnalyticsUseCase
) : BaseViewModel<LoginUiState>(LoginScreenAnalytic, LoginUiState()), LoginCommandReceiver {

    override val commandReceiver = this

    private val loginFormModel get() = uiState.value.loginFormModel

    init {
        checkNeedsLogin()
    }

    override fun checkShowInvalidEmailMsg() {
        if (loginFormModel.emailIsNotEmpty) {
            updateUiState { it.setInvalidEmail(!loginFormModel.emailIsValid) }
        }
    }

    override fun login() {
        runAsyncTask(
            onError = ::handleLoginError,
            onCompletion = { updateUiState { it.setLoading(false) } },
            onStart = {
                analyticsUseCase(LoginClickEventAnalytic)
                updateUiState { it.setLoading(true).setShowInvalidCredentialsMsg(false) }
            }
        ) {
            authenticationUseCase(
                AuthenticationCredentialsModel(loginFormModel.email, loginFormModel.password)
            )
            navigateToHome()
        }
    }

    override fun writeData(email: String?, password: String?) {
        updateUiState { it.setLoginForm(email, password) }
        stateScreenWritingManager()
    }

    override fun setCustomTheme(enterScreen: Boolean, currentAppTheme: Theme) {
        val theme = if (enterScreen && currentAppTheme != Theme.DARK)
            Theme.DARK
        else null
        appThemeManager.setCustomTheme(theme)
    }

    override fun handleLoginError(throwable: Throwable?) {
        updateUiState {
            if (throwable is InvalidCredentialsThrowable) {
                it.setShowInvalidCredentialsMsg(true)
            } else {
                it.setAlertDialogError(throwable.errorAlertDialogParam)
            }
        }
    }

    private fun checkNeedsLogin() = runAsyncTask {
        if (sessionManager.needsLogin()) {
            openScreenAnalytic()
            initState()
        } else {
            navigateToHome()
        }
    }

    private fun initState() {
        updateUiState {
            it.initState(
                versionName = buildConfigProvider.get.versionNameForView,
                enableLoginButton = buildConfigProvider.get.isDebug
            )
        }
    }

    private suspend fun navigateToHome() {
        navigationManager.asyncNavigate(
            HomeDestination("teste", InnerHome("te", "23232")),
            NavigationMode.REMOVE_ALL_SCREENS_STACK
        )
    }

    private fun stateScreenWritingManager() {
        updateUiState {
            it
                .setShowInvalidCredentialsMsg(false)
                .setEnabledButton(loginFormModel.loginAlready || buildConfigProvider.get.isDebug)
        }
    }
}