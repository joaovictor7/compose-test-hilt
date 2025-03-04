package com.composetest.feature.login.ui.login

import com.composetest.common.errors.ApiError
import com.composetest.core.analytic.AnalyticSender
import com.composetest.core.analytic.events.configuration.ThemeConfigurationScreenAnalytic
import com.composetest.core.analytic.events.login.LoginEventAnalytic
import com.composetest.core.designsystem.utils.getCommonSimpleDialogErrorParam
import com.composetest.core.domain.enums.Theme
import com.composetest.core.domain.providers.BuildConfigProvider
import com.composetest.core.domain.usecases.configuration.SetSystemBarsStyleUseCase
import com.composetest.core.domain.usecases.login.AuthenticationByBiometricUseCase
import com.composetest.core.domain.usecases.login.AuthenticationUseCase
import com.composetest.core.domain.usecases.login.BiometricIsEnableUseCase
import com.composetest.core.domain.usecases.remoteconfigs.GetBooleanRemoteConfigUseCase
import com.composetest.core.router.destinations.login.LoginDestination
import com.composetest.core.router.destinations.root.RootDestination
import com.composetest.core.router.enums.NavigationMode
import com.composetest.core.router.models.NavigationModel
import com.composetest.core.security.enums.BiometricError
import com.composetest.core.security.enums.BiometricError.Companion.biometricIsLockout
import com.composetest.core.security.enums.BiometricError.Companion.userClosedPrompt
import com.composetest.core.security.providers.BiometricProvider
import com.composetest.core.security.providers.CipherProvider
import com.composetest.core.ui.bases.BaseViewModel
import com.composetest.core.ui.interfaces.UiEvent
import com.composetest.core.ui.interfaces.UiState
import com.composetest.feature.login.R
import com.composetest.feature.login.enums.LoginRemoteConfig
import com.composetest.feature.login.extensions.autoShowBiometricPrompt
import com.composetest.feature.login.models.BiometricModel
import com.composetest.feature.login.ui.dialogs.SimpleDialogParam
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
internal class LoginViewModel @Inject constructor(
    private val buildConfigProvider: BuildConfigProvider,
    private val biometricProvider: BiometricProvider,
    private val cipherProvider: CipherProvider,
    private val authenticationUseCase: AuthenticationUseCase,
    private val authenticationByBiometricUseCase: AuthenticationByBiometricUseCase,
    private val biometricIsEnableUseCase: BiometricIsEnableUseCase,
    private val setSystemBarsStyleUseCase: SetSystemBarsStyleUseCase,
    private val getBooleanRemoteConfigUseCase: GetBooleanRemoteConfigUseCase,
    private val loginDestination: LoginDestination,
    override val analyticSender: AnalyticSender,
) : BaseViewModel(), UiState<LoginUiState>, UiEvent<LoginUiEvent>, LoginCommandReceiver {

    override val commandReceiver = this
    override val analyticScreen = ThemeConfigurationScreenAnalytic

    private val loginFormModel get() = uiState.value.loginFormModel
    private val byPassLogin by lazy { getBooleanRemoteConfigUseCase(LoginRemoteConfig.BY_PASS_LOGIN) }

    private val _uiState = MutableStateFlow(LoginUiState())
    override val uiState = _uiState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<LoginUiEvent>(replay = 1)
    override val uiEvent = _uiEvent.asSharedFlow()

    init {
        openScreenAnalytic()
        initUiState()
    }

    override fun checkShowInvalidEmailMsg(hasFocus: Boolean) {
        if (hasFocus) {
            _uiState.update { it.setInvalidEmail(false) }
        } else if (loginFormModel.emailIsNotEmpty) {
            _uiState.update { it.setInvalidEmail(!loginFormModel.emailIsValid) }
        }
    }

    override fun login(byBiometric: Boolean) {
        _uiState.update { it.setLoading(true) }
        runAsyncTask(
            onError = ::handleLoginError,
            onCompletion = { _uiState.update { it.setLoading(false) } }
        ) {
            authenticate(byBiometric)
            analyticSender.sendEvent(LoginEventAnalytic.LoginSuccessful(true))
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
        _uiState.update {
            it.setLoginForm(
                newLoginFormModel,
                newLoginFormModel.loginAlready || byPassLogin
            )
        }
    }

    override fun setStatusBarsTheme(enterScreen: Boolean, currentAppTheme: Theme) {
        val theme = if (enterScreen && currentAppTheme != Theme.DARK) Theme.DARK else null
        setSystemBarsStyleUseCase(theme)
    }

    override fun dismissSimpleDialog() {
        _uiState.update { it.setSimpleDialog(null) }
    }

    override fun biometricErrorHandler(biometricError: BiometricError) {
        if (biometricError.userClosedPrompt) return
        if (biometricError.biometricIsLockout) {
            _uiState.update {
                it.setBiometricModel(
                    BiometricModel(
                        isAvailable = false,
                        messageId = R.string.feature_login_biometric_is_blocked
                    )
                )
            }
        } else {
            _uiState.update { it.setBiometricModel(BiometricModel(isError = true)) }
        }
    }

    override fun biometricErrorAnimationFinished() {
        _uiState.update { it.setBiometricModel(BiometricModel()) }
    }

    override fun showBiometricPrompt() {
        _uiEvent.emitEvent(LoginUiEvent.ShowBiometricPrompt)
    }

    private fun initUiState() {
        val biometricIsAvailable = biometricProvider.biometricIsAvailable
        runAsyncTask {
            val biometricIsEnable = biometricIsEnableUseCase()
            autoShowBiometricPrompt(biometricIsEnable, biometricIsAvailable)
            _uiState.update {
                it.initUiState(
                    simpleDialogParam = showDialogExpiredSession(),
                    versionName = "${buildConfigProvider.buildConfig.versionName} - ${buildConfigProvider.buildConfig.versionCode}",
                    loginButtonIsEnabled = byPassLogin,
                    biometricModel = if (biometricIsAvailable && biometricIsEnable) BiometricModel() else null,
                )
            }
        }
    }

    private suspend fun handleLoginError(throwable: Throwable?) {
        analyticSender.sendEvent(LoginEventAnalytic.LoginSuccessful(false))
        _uiState.update { uiState ->
            if (throwable is ApiError.Unauthorized) {
                uiState.setShowInvalidCredentialsMsg(true)
            } else {
                uiState.setSimpleDialog(getCommonSimpleDialogErrorParam(throwable))
            }
        }
    }

    private suspend fun authenticate(byBiometric: Boolean) {
        if (byBiometric) {
            authenticationByBiometricUseCase()
        } else {
            authenticationUseCase(
                loginFormModel.email,
                cipherProvider.encrypt(loginFormModel.password)
            )
        }
    }

    private fun navigateToRoot() {
        _uiEvent.emitEvent(
            LoginUiEvent.NavigateTo(
                NavigationModel(RootDestination, NavigationMode.REMOVE_ALL_SCREENS_STACK)
            )
        )
    }

    private fun showDialogExpiredSession() = if (loginDestination.expiredSession) {
        SimpleDialogParam.ExpiredSession
    } else null

    private fun autoShowBiometricPrompt(biometricIsEnable: Boolean, biometricIsAvailable: Boolean) {
        if (loginDestination.autoShowBiometricPrompt && biometricIsEnable && biometricIsAvailable) {
            showBiometricPrompt()
        }
    }
}