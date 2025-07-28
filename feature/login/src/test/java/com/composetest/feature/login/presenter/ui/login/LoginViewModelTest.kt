package com.composetest.feature.login.presenter.ui.login

import com.composetest.core.analytic.api.event.CommonAnalyticEvent
import com.composetest.core.analytic.api.sender.AnalyticSender
import com.composetest.core.designsystem.extension.dialogErrorNavigation
import com.composetest.core.domain.enums.BuildType
import com.composetest.core.domain.enums.Flavor
import com.composetest.core.domain.model.buildconfig.BuildConfigFieldsModel
import com.composetest.core.domain.model.buildconfig.BuildConfigModel
import com.composetest.core.domain.provider.BuildConfigProvider
import com.composetest.core.domain.usecase.configuration.SetSystemBarsStyleUseCase
import com.composetest.core.domain.usecase.remoteconfig.GetBooleanRemoteConfigUseCase
import com.composetest.core.network.model.ApiError
import com.composetest.core.router.destination.login.LoginDestination
import com.composetest.core.router.destination.root.RootDestination
import com.composetest.core.router.enums.NavigationMode
import com.composetest.core.router.model.NavigationModel
import com.composetest.core.security.api.provider.BiometricProvider
import com.composetest.core.security.api.provider.CipherProvider
import com.composetest.core.test.android.BaseTest
import com.composetest.core.test.kotlin.extension.runFlowTest
import com.composetest.core.ui.util.AsyncTaskUtils
import com.composetest.feature.login.analytic.event.LoginEventAnalytic
import com.composetest.feature.login.analytic.screen.LoginScreenAnalytic
import com.composetest.feature.login.domain.usecase.AuthenticationByBiometricUseCase
import com.composetest.feature.login.domain.usecase.AuthenticationUseCase
import com.composetest.feature.login.domain.usecase.BiometricIsEnableUseCase
import com.composetest.feature.login.presenter.model.LoginFormModel
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
    fun `initial uiState`() = runFlowTest(
        flow = viewModel.uiState,
        onVerify = { uiStates ->
            assertEquals(LoginUiState(versionName = "1.0.0 - 0"), uiStates[0])
            coVerifySequence {
                analyticSender.sendEvent(CommonAnalyticEvent.OpenScreen(LoginScreenAnalytic))
            }
        },
    )

    @Test
    fun `misleading credentials login`() = runFlowTest(
        flow = viewModel.uiState,
        onSetup = {
            coEvery { authenticationUseCase(any(), any()) } throws ApiError.Unauthorized()
        },
        onTrigger = {
            viewModel.executeIntent(LoginIntent.WriteData("teste@teste.com", "password"))
            viewModel.executeIntent(LoginIntent.Login(false))
        },
        onVerify = { uiStates ->
            assertTrue(uiStates[1].loginButtonIsEnabled)
            assertEquals(
                LoginFormModel("teste@teste.com", "password"),
                uiStates[1].loginFormModel
            )
            assertTrue(uiStates[2].isLoading)
            assertTrue(uiStates[3].invalidCredentials)
            assertFalse(uiStates[3].isLoading)
            coVerify {
                analyticSender.sendEvent(LoginEventAnalytic.LoginSuccessful(false))
            }
        },
    )

    @Test
    fun `success login`() = runFlowTest(
        firstFlow = viewModel.uiState,
        secondFlow = viewModel.uiEvent,
        onSetup = {
            viewModel.executeIntent(
                LoginIntent.WriteData(email = "teste@teste.com", password = "password")
            )
            viewModel.executeIntent(LoginIntent.Login(false))
        },
        onVerify = { uiStates, uiEvents ->
            assertTrue(uiStates[1].loginButtonIsEnabled)
            assertEquals(
                LoginFormModel("teste@teste.com", "password"),
                uiStates[1].loginFormModel
            )
            assertTrue(uiStates[2].isLoading)
            assertFalse(uiStates[3].isLoading)
            assertEquals(
                LoginUiEvent.NavigateTo(
                    NavigationModel(RootDestination, NavigationMode.REMOVE_ALL_SCREENS_STACK)
                ),
                uiEvents[0]
            )
            coVerifyOrder {
                authenticationUseCase("teste@teste.com", "encryptedData")
                analyticSender.sendEvent(LoginEventAnalytic.LoginSuccessful(true))
            }
        },
    )

    @Test
    fun `error network`() = runFlowTest(
        flow = viewModel.uiEvent,
        onSetup = {
            coEvery { authenticationUseCase(any(), any()) } throws ApiError.Network()
        },
        onTrigger = {
            viewModel.executeIntent(LoginIntent.WriteData("teste@teste.com", "password"))
            viewModel.executeIntent(LoginIntent.Login(false))
        },
        onVerify = { uiEvents ->
            val expectedNetworkError = ApiError.Network()
            assertEquals(
                LoginUiEvent.NavigateTo(expectedNetworkError.dialogErrorNavigation()),
                uiEvents[0]
            )
        },
    )

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
        asyncTaskUtils = AsyncTaskUtils(analyticSender, LoginScreenAnalytic)
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