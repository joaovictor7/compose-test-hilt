package com.composetest.feature.login.ui.login

import com.composetest.common.enums.Flavor
import com.composetest.common.providers.BuildConfigProvider
import com.composetest.core.designsystem.utils.getDefaultSimpleDialogErrorParam
import com.composetest.core.domain.enums.Theme
import com.composetest.core.domain.errors.ApiError
import com.composetest.core.domain.managers.AppThemeManager
import com.composetest.core.domain.managers.RemoteConfigManager
import com.composetest.core.domain.managers.SessionManager
import com.composetest.core.domain.models.AuthenticationCredentialsModel
import com.composetest.core.domain.usecases.AuthenticationUseCase
import com.composetest.core.domain.usecases.SendAnalyticsUseCase
import com.composetest.core.router.destinations.root.RootDestination
import com.composetest.core.router.di.qualifiers.NavGraphQualifier
import com.composetest.core.router.enums.NavGraph
import com.composetest.core.router.enums.NavigationMode
import com.composetest.core.router.managers.NavigationManager
import com.composetest.core.ui.bases.BaseViewModel
import com.composetest.feature.login.R
import com.composetest.feature.login.analytics.login.LoginClickEventAnalytic
import com.composetest.feature.login.analytics.login.LoginEventAnalytic
import com.composetest.feature.login.analytics.login.LoginScreenAnalytic
import com.composetest.feature.login.remoteconfigs.LoginRemoteConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class LoginViewModel @Inject constructor(
    private val buildConfigProvider: BuildConfigProvider,
    private val appThemeManager: AppThemeManager,
    private val authenticationUseCase: AuthenticationUseCase,
    private val sessionManager: SessionManager,
    private val remoteConfigManager: RemoteConfigManager,
    override val sendAnalyticsUseCase: SendAnalyticsUseCase,
    @NavGraphQualifier(NavGraph.MAIN) override val navigationManager: NavigationManager
) : BaseViewModel<LoginUiState, LoginUiEvent>(LoginScreenAnalytic, LoginUiState()),
    LoginCommandReceiver {

    override val commandReceiver = this

    private val loginFormModel get() = uiState.value.loginFormModel
    private val byPassLogin by lazy { remoteConfigManager.getBoolean(LoginRemoteConfig.BypassLogin) }

    override fun initUiState() {
        checkNeedsLogin()
    }

    override fun checkShowInvalidEmailMsg(hasFocus: Boolean) {
        if (hasFocus) {
            updateUiState { it.setInvalidEmail(false) }
        } else if (loginFormModel.emailIsNotEmpty) {
            updateUiState { it.setInvalidEmail(!loginFormModel.emailIsValid) }
        }
    }

    override fun login() {
        updateUiState { it.setLoading(true) }
        runAsyncTask(
            onError = ::handleLoginError,
            onCompletion = { updateUiState { it.setLoading(false) } }
        ) {
            sendAnalyticsUseCase(LoginClickEventAnalytic.LoginButton)
            authenticationUseCase(
                AuthenticationCredentialsModel(loginFormModel.email, loginFormModel.password)
            )
            sendAnalyticsUseCase(LoginEventAnalytic.LoginSuccessful(true))
            navigateToRoot()
        }
    }

    override fun writeData(email: String?, password: String?) {
        var newLoginFormModel = loginFormModel
        email?.let {
            newLoginFormModel = newLoginFormModel.copy(email = it)
        }
        password?.let {
            newLoginFormModel = newLoginFormModel.copy(password = it)
        }
        updateUiState {
            it.setLoginForm(newLoginFormModel, newLoginFormModel.loginAlready || byPassLogin)
        }
    }

    override fun setCustomTheme(enterScreen: Boolean, currentAppTheme: Theme) {
        val theme = if (enterScreen && currentAppTheme != Theme.DARK)
            Theme.DARK
        else null
        runAsyncTask {
            appThemeManager.setCustomTheme(theme)
        }
    }

    override fun dismissSimpleDialog() {
        updateUiState { it.setSimpleDialog(null) }
    }

    private suspend fun handleLoginError(throwable: Throwable?) {
        sendAnalyticsUseCase(LoginEventAnalytic.LoginSuccessful(false))
        updateUiState { uiState ->
            if (throwable is ApiError.Unauthorized) {
                uiState.setShowInvalidCredentialsMsg(true)
            } else {
                uiState.setSimpleDialog(getDefaultSimpleDialogErrorParam(throwable))
            }
        }
    }

    private fun checkNeedsLogin() = runAsyncTask {
        if (sessionManager.needsLogin()) {
            showScreen()
        } else {
            navigateToRoot()
        }
    }

    private fun showScreen() {
        openScreenAnalytic()
        updateUiState {
            it.initUiState(
                versionName = "${buildConfigProvider.get.versionName} - ${buildConfigProvider.get.versionCode}",
                distributionTextId = when (buildConfigProvider.get.flavor) {
                    Flavor.FREE -> R.string.feature_login_limited_distribution
                    Flavor.FULL -> R.string.feature_login_full_distribution
                },
                enableLoginButton = byPassLogin
            )
        }
    }

    private suspend fun navigateToRoot() {
        navigationManager.asyncNavigate(RootDestination, NavigationMode.REMOVE_ALL_SCREENS_STACK)
    }
}