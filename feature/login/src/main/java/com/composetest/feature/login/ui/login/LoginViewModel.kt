package com.composetest.feature.login.ui.login

import com.composetest.common.providers.BuildConfigProvider
import com.composetest.core.designsystem.utils.getDefaultSimpleDialogErrorParam
import com.composetest.core.domain.enums.Theme
import com.composetest.core.domain.errors.ApiError
import com.composetest.core.domain.managers.ConfigurationManager
import com.composetest.core.domain.managers.RemoteConfigManager
import com.composetest.core.domain.managers.SessionManager
import com.composetest.core.domain.usecases.AuthenticationByBiometricUseCase
import com.composetest.core.domain.usecases.AuthenticationUseCase
import com.composetest.core.domain.usecases.BiometricIsAvailableUseCase
import com.composetest.core.domain.usecases.SendAnalyticsUseCase
import com.composetest.core.router.destinations.root.RootDestination
import com.composetest.core.router.di.qualifiers.NavGraphQualifier
import com.composetest.core.router.enums.NavGraph
import com.composetest.core.router.enums.NavigationMode
import com.composetest.core.router.managers.NavigationManager
import com.composetest.core.security.enums.BiometricError
import com.composetest.core.security.enums.BiometricError.Companion.biometricIsLockout
import com.composetest.core.security.enums.BiometricError.Companion.userClosedPrompt
import com.composetest.core.ui.bases.BaseViewModel
import com.composetest.feature.login.R
import com.composetest.feature.login.analytics.login.LoginEventAnalytic
import com.composetest.feature.login.analytics.login.LoginScreenAnalytic
import com.composetest.feature.login.models.BiometricModel
import com.composetest.feature.login.remoteconfigs.LoginRemoteConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class LoginViewModel @Inject constructor(
    private val buildConfigProvider: BuildConfigProvider,
    private val configurationManager: ConfigurationManager,
    private val authenticationUseCase: AuthenticationUseCase,
    private val authenticationByBiometricUseCase: AuthenticationByBiometricUseCase,
    private val biometricIsAvailableUseCase: BiometricIsAvailableUseCase,
    private val sessionManager: SessionManager,
    private val remoteConfigManager: RemoteConfigManager,
    override val sendAnalyticsUseCase: SendAnalyticsUseCase,
    @NavGraphQualifier(NavGraph.MAIN) override val navigationManager: NavigationManager
) : BaseViewModel<LoginUiState, LoginUiEvent>(LoginUiState()),
    LoginCommandReceiver {

    private val loginFormModel get() = uiState.value.loginFormModel
    private val byPassLogin by lazy { remoteConfigManager.getBoolean(LoginRemoteConfig.ByPassLogin) }

    override val commandReceiver = this
    override val analyticScreen = LoginScreenAnalytic

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

    override fun login(byBiometric: Boolean) {
        updateUiState { it.setLoading(true) }
        runAsyncTask(
            onError = ::handleLoginError,
            onCompletion = { updateUiState { it.setLoading(false) } }
        ) {
            authenticate(byBiometric)
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
            it.setLoginForm(
                newLoginFormModel,
                newLoginFormModel.loginAlready || byPassLogin
            )
        }
    }

    override fun setStatusBarsTheme(enterScreen: Boolean, currentAppTheme: Theme) {
        val theme = if (enterScreen && currentAppTheme != Theme.DARK)
            Theme.DARK
        else null
        configurationManager.setStatusBarsTheme(theme)
    }

    override fun dismissSimpleDialog() {
        updateUiState { it.setSimpleDialog(null) }
    }

    override fun biometricErrorHandler(biometricError: BiometricError) {
        if (biometricError.userClosedPrompt) return
        if (biometricError.biometricIsLockout) {
            updateUiState {
                it.setBiometricModel(
                    BiometricModel(
                        isAvailable = false,
                        messageId = R.string.feature_login_biometric_is_blocked
                    )
                )
            }
        } else {
            updateUiState { it.setBiometricModel(BiometricModel(isError = true)) }
        }
    }

    override fun biometricErrorAnimationFinished() {
        updateUiState { it.setBiometricModel(BiometricModel()) }
    }

    override fun showBiometricPrompt() {
        launchUiEvent(LoginUiEvent.ShowBiometricPrompt)
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

    private suspend fun authenticate(byBiometric: Boolean) {
        if (byBiometric) {
            authenticationByBiometricUseCase()
        } else {
            authenticationUseCase(loginFormModel.email, loginFormModel.password)
        }
    }

    private fun showScreen() {
        openScreenAnalytic()
        runAsyncTask {
            val biometricIsAvailable = biometricIsAvailableUseCase()
            updateUiState {
                it.initUiState(
                    versionName = "${buildConfigProvider.get.versionName} - ${buildConfigProvider.get.versionCode}",
                    loginButtonIsEnabled = byPassLogin,
                    biometricModel = if (biometricIsAvailable) BiometricModel() else null,
                )
            }
            if (biometricIsAvailable) {
                launchUiEvent(LoginUiEvent.ShowBiometricPrompt)
            }
        }
    }

    private suspend fun navigateToRoot() {
        navigationManager.asyncNavigate(RootDestination, NavigationMode.REMOVE_ALL_SCREENS_STACK)
    }
}