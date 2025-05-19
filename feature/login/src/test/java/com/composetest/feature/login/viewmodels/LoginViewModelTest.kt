package com.composetest.feature.login.viewmodels

import com.composetest.common.errors.ApiError
import com.composetest.core.analytic.AnalyticSender
import com.composetest.core.analytic.enums.ScreensAnalytic
import com.composetest.core.analytic.events.CommonAnalyticEvent
import com.composetest.core.analytic.events.login.LoginEventAnalytic
import com.composetest.core.domain.enums.BuildType
import com.composetest.core.domain.enums.Flavor
import com.composetest.core.domain.models.buildconfig.BuildConfigFieldsModel
import com.composetest.core.domain.models.buildconfig.BuildConfigModel
import com.composetest.core.domain.providers.BuildConfigProvider
import com.composetest.core.domain.usecases.configuration.SetSystemBarsStyleUseCase
import com.composetest.core.domain.usecases.remoteconfig.GetBooleanRemoteConfigUseCase
import com.composetest.core.router.destinations.login.LoginDestination
import com.composetest.core.router.destinations.root.RootDestination
import com.composetest.core.router.enums.NavigationMode
import com.composetest.core.router.models.NavigationModel
import com.composetest.core.security.providers.BiometricProvider
import com.composetest.core.security.providers.CipherProvider
import com.composetest.core.test.BaseTest
import com.composetest.core.test.extensions.runFlowTest
import com.composetest.feature.login.domain.usecases.AuthenticationByBiometricUseCase
import com.composetest.feature.login.domain.usecases.AuthenticationUseCase
import com.composetest.feature.login.domain.usecases.BiometricIsEnableUseCase
import com.composetest.feature.login.presenter.models.LoginFormModel
import com.composetest.feature.login.presenter.ui.login.LoginCommand
import com.composetest.feature.login.presenter.ui.login.LoginUiEvent
import com.composetest.feature.login.presenter.ui.login.LoginUiState
import com.composetest.feature.login.presenter.ui.login.LoginViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.coVerifyOrder
import io.mockk.coVerifySequence
import io.mockk.mockk
import kotlinx.coroutines.test.TestDispatcher
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class LoginViewModelTest : BaseTest() {

    private val biometricProvider: BiometricProvider = mockk(relaxed = true)
    private val cipherProvider: CipherProvider = mockk {
        coEvery { encrypt(any()) } returns "encryptedData"
    }
    private val authenticationUseCase: AuthenticationUseCase = mockk(relaxed = true)
    private val authenticationByBiometricUseCase: AuthenticationByBiometricUseCase =
        mockk(relaxed = true)
    private val setSystemBarsStyleUseCase: SetSystemBarsStyleUseCase = mockk(relaxed = true)
    private val getBooleanRemoteConfigUseCase: GetBooleanRemoteConfigUseCase = mockk(relaxed = true)
    private val analyticSender: AnalyticSender = mockk(relaxed = true)
    private val biometricIsEnableUseCase: BiometricIsEnableUseCase = mockk(relaxed = true)
    private val buildConfigProvider: BuildConfigProvider = object : BuildConfigProvider {
        override val buildConfig: BuildConfigModel = buildConfigModelMock
        override val buildConfigFields = buildConfigFieldsModelMock
    }

    private lateinit var viewModel: LoginViewModel

    override lateinit var testDispatcher: TestDispatcher

    @BeforeEach
    fun before() {
        viewModel = initViewModel()
    }

    @Test
    fun `initial uiState`() = runFlowTest(viewModel.uiState) { onCancelJob, states ->
        onCancelJob()
        assertEquals(LoginUiState(versionName = "1.0.0 - 0"), states[0])
        coVerifySequence {
            analyticSender.sendEvent(CommonAnalyticEvent.OpenScreen(ScreensAnalytic.LOGIN))
        }
    }

    @Test
    fun `misleading credentials login`() =
        runFlowTest(viewModel.uiState) { onCancelJob, states ->
            coEvery { authenticationUseCase(any(), any()) } throws ApiError.Unauthorized()

            viewModel.executeCommand(LoginCommand.WriteData("teste@teste.com", "password"))
            viewModel.executeCommand(LoginCommand.Login(false))
            onCancelJob()

            assertTrue(states[1].loginButtonIsEnabled)
            assertEquals(
                LoginFormModel("teste@teste.com", "password"),
                states[1].loginFormModel
            )
            assertTrue(states[2].isLoading)
            assertTrue(states[3].invalidCredentials)
            assertFalse(states[3].isLoading)
            coVerify {
                analyticSender.sendEvent(LoginEventAnalytic.LoginSuccessful(false))
            }
        }

    @Test
    fun `success login`() = runFlowTest(
        viewModel.uiState,
        viewModel.uiEvent
    ) { onCancelJob, firstStates, secondStates ->
        viewModel.executeCommand(
            LoginCommand.WriteData(email = "teste@teste.com", password = "password")
        )
        viewModel.executeCommand(LoginCommand.Login(false))
        onCancelJob()

        assertTrue(firstStates[1].loginButtonIsEnabled)
        assertEquals(
            LoginFormModel("teste@teste.com", "password"),
            firstStates[1].loginFormModel
        )
        assertTrue(firstStates[2].isLoading)
        assertFalse(firstStates[3].isLoading)
        assertEquals(
            LoginUiEvent.NavigateTo(
                NavigationModel(RootDestination, NavigationMode.REMOVE_ALL_SCREENS_STACK)
            ), secondStates[0]
        )
        coVerifyOrder {
            authenticationUseCase("teste@teste.com", "encryptedData")
            analyticSender.sendEvent(LoginEventAnalytic.LoginSuccessful(true))
        }
    }

    @Test
    fun `error network`() =
        runFlowTest(
            viewModel.uiState,
            viewModel.uiEvent
        ) { onCancelJob, firstStates, secondStates ->
            coEvery { authenticationUseCase(any(), any()) } throws ApiError.Network()

            viewModel.executeCommand(LoginCommand.WriteData("teste@teste.com", "password"))
            viewModel.executeCommand(LoginCommand.Login(false))
            onCancelJob()

            assertEquals(CommonSimpleDialog.NetworkError, firstStates[3].simpleDialogParam)
        }

    private fun initViewModel() = LoginViewModel(
        buildConfigProvider = buildConfigProvider,
        biometricProvider = biometricProvider,
        cipherProvider = cipherProvider,
        authenticationUseCase = authenticationUseCase,
        authenticationByBiometricUseCase = authenticationByBiometricUseCase,
        biometricIsEnableUseCase = biometricIsEnableUseCase,
        setSystemBarsStyleUseCase = setSystemBarsStyleUseCase,
        getBooleanRemoteConfigUseCase = getBooleanRemoteConfigUseCase,
        loginDestination = LoginDestination(),
        analyticSender = analyticSender,
        asyncTaskUtils = mockk(relaxed = true)
    )

    private companion object {
        val buildConfigModelMock = BuildConfigModel(
            applicationId = "app",
            versionName = "1.0.0",
            versionCode = 0,
            buildType = BuildType.DEBUG,
            flavor = Flavor.DEVELOP,
            androidSdkVersion = 34,
        )
        val buildConfigFieldsModelMock = BuildConfigFieldsModel(
            coinApiHost = "123",
            newsApiHost = "123",
            openWeatherApiHost = "123",
            openWeatherIconHost = "123",
        )
    }
}