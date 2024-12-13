package com.composetest.feature.login.viewmodels

import com.composetest.common.enums.BuildType
import com.composetest.common.enums.Flavor
import com.composetest.common.enums.FlavorDimension
import com.composetest.common.models.BuildConfigFieldsModel
import com.composetest.common.models.BuildConfigModel
import com.composetest.common.providers.BuildConfigProvider
import com.composetest.core.designsystem.params.alertdialogs.SimpleDialogParam
import com.composetest.core.domain.managers.SessionManager
import com.composetest.core.domain.errors.network.NetworkThrowable
import com.composetest.core.domain.usecases.AuthenticationUseCase
import com.composetest.core.router.destinations.root.RootDestination
import com.composetest.core.router.enums.NavigationMode
import com.composetest.core.router.managers.NavigationManager
import com.composetest.core.test.extensions.runFlowTest
import com.composetest.core.test.interfaces.CoroutinesTest
import com.composetest.feature.login.R
import com.composetest.feature.login.models.LoginFormModel
import com.composetest.feature.login.ui.login.LoginCommand
import com.composetest.feature.login.ui.login.LoginUiState
import com.composetest.feature.login.ui.login.LoginViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.withContext
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class LoginViewModelTest : CoroutinesTest {

    override lateinit var testDispatcher: TestDispatcher

    private val buildConfigModelMock = BuildConfigModel(
        applicationId = "app",
        versionName = "1.0.0",
        versionCode = 0,
        buildType = BuildType.DEBUG,
        flavorDimension = FlavorDimension.DEVELOP,
        flavor = Flavor.FULL,
        androidSdkVersion = 34,
        buildConfigFieldsModel = BuildConfigFieldsModel(
            bffApiHost = String(),
            bffApiPort = 0,
            newsApiKey = String()
        )
    )
    private val navigationManager: NavigationManager = mockk(relaxed = true)
    private val buildConfigProvider: BuildConfigProvider = object : BuildConfigProvider {
        override val get: BuildConfigModel = buildConfigModelMock
    }
    private val authenticationUseCase: AuthenticationUseCase = mockk()
    private val sessionManager: SessionManager = mockk {
        coEvery { needsLogin() } returns true
    }
    private val networkAlertDialogParam = SimpleDialogParam.getNetworkAlertDialogParam { }

    private lateinit var viewModel: LoginViewModel

    @BeforeEach
    fun before() {
//        viewModel = initViewModel()
    }

    @Test
    fun `initial uiState`() = runFlowTest(viewModel.uiState) { onCancelJob, collectedStates ->
        coEvery { sessionManager.needsLogin() } returns false
        onCancelJob()

        assertEquals(
            listOf(
                LoginUiState(),
                LoginUiState(
                    needsLogin = true,
                    distributionTextId = R.string.feature_login_full_distribution,
                    versionName = "1.0.0 - 0"
                )
            ),
            collectedStates
        )
    }

    @Test
    fun `initial uiState when not need login`() {
        coEvery { sessionManager.needsLogin() } returns false
//        val viewModel = initViewModel()
//        runFlowTest(viewModel.uiState) { onCancelJob, collectedStates ->
//            onCancelJob()
//
//            assertEquals(
//                listOf(LoginUiState()),
//                collectedStates
//            )
//            coVerify {
//                navigationManager.asyncNavigate(
//                    RootDestination,
//                    NavigationMode.REMOVE_ALL_SCREENS_STACK
//                )
//            }
//        }
    }

    @Test
    fun `misleanding credentials login`() =
        runFlowTest(viewModel.uiState) { onCancelJob, collectedStates ->
            coEvery {
                authenticationUseCase.invoke(any())
            } coAnswers { withContext(testDispatcher) { throw InvalidCredentialsThrowable() } }

            viewModel.executeCommand(LoginCommand.WriteData("teste@teste.com", "password"))
            viewModel.executeCommand(LoginCommand.Login)
            onCancelJob()

            assertEquals(
                listOf(
                    LoginUiState(
                        needsLogin = true,
                        versionName = "1.0.0 - 0",
                        enableLoginButton = true
                    ),
                    LoginUiState(
                        needsLogin = true,
                        versionName = "1.0.0 - 0",
                        enableLoginButton = true,
                        loginFormModel = LoginFormModel(
                            email = "teste@teste.com",
                            password = "password"
                        )
                    ),
                    LoginUiState(
                        needsLogin = true,
                        versionName = "1.0.0 - 0",
                        enableLoginButton = true,
                        isLoading = true,
                        loginFormModel = LoginFormModel(
                            email = "teste@teste.com",
                            password = "password"
                        )
                    ),
                    LoginUiState(
                        needsLogin = true,
                        versionName = "1.0.0 - 0",
                        enableLoginButton = true,
                        invalidCredentials = true,
                        isLoading = true,
                        loginFormModel = LoginFormModel(
                            email = "teste@teste.com",
                            password = "password"
                        )
                    ),
                    LoginUiState(
                        needsLogin = true,
                        versionName = "1.0.0 - 0",
                        enableLoginButton = true,
                        invalidCredentials = true,
                        loginFormModel = LoginFormModel(
                            email = "teste@teste.com",
                            password = "password"
                        )
                    )
                ),
                collectedStates
            )
        }

    @Test
    fun `success login`() =
        runFlowTest(viewModel.uiState) { onCancelJob, collectedStates ->
            coEvery {
                authenticationUseCase(any())
            } coAnswers { withContext(testDispatcher) {} }

            viewModel.executeCommand(LoginCommand.WriteData("teste@teste.com", "password"))
            viewModel.executeCommand(LoginCommand.Login)
            onCancelJob()

            assertEquals(
                listOf(
                    LoginUiState(),
                    LoginUiState(
                        versionName = "1.0.0 - 0",
                        needsLogin = true,
                        distributionTextId = R.string.feature_login_full_distribution,
                    ),
                    LoginUiState(
                        versionName = "1.0.0 - 0",
                        needsLogin = true,
                        distributionTextId = R.string.feature_login_full_distribution,
                        loginFormModel = LoginFormModel(
                            email = "teste@teste.com",
                            password = "password"
                        )
                    ),
                    LoginUiState(
                        needsLogin = true,
                        versionName = "1.0.0 - 0",
                        enableLoginButton = true,
                        distributionTextId = R.string.feature_login_full_distribution,
                        loginFormModel = LoginFormModel(
                            email = "teste@teste.com",
                            password = "password"
                        )
                    ),
                    LoginUiState(
                        needsLogin = true,
                        versionName = "1.0.0 - 0",
                        enableLoginButton = true,
                        distributionTextId = R.string.feature_login_full_distribution,
                        isLoading = true,
                        loginFormModel = LoginFormModel(
                            email = "teste@teste.com",
                            password = "password"
                        )
                    ),
                    LoginUiState(
                        needsLogin = true,
                        versionName = "1.0.0 - 0",
                        enableLoginButton = true,
                        distributionTextId = R.string.feature_login_full_distribution,
                        loginFormModel = LoginFormModel(
                            email = "teste@teste.com",
                            password = "password"
                        )
                    )
                ),
                collectedStates
            )
            coVerify {
                navigationManager.asyncNavigate(
                    RootDestination,
                    NavigationMode.REMOVE_ALL_SCREENS_STACK
                )
            }
        }

    @Test
    fun `error network`() =
        runFlowTest(viewModel.uiState) { onCancelJob, collectedStates ->
            coEvery {
                authenticationUseCase(any())
            } coAnswers { withContext(testDispatcher) { throw NetworkThrowable() } }

            viewModel.executeCommand(LoginCommand.WriteData("teste@teste.com", "password"))
            viewModel.executeCommand(LoginCommand.Login)
            onCancelJob()

            assertEquals(
                listOf(
                    LoginUiState(),
                    LoginUiState(
                        needsLogin = true,
                        versionName = "1.0.0 - 0",
                        enableLoginButton = true
                    ),
                    LoginUiState(
                        needsLogin = true,
                        versionName = "1.0.0 - 0",
                        enableLoginButton = true,
                        loginFormModel = LoginFormModel(
                            email = "teste@teste.com",
                            password = "password"
                        )
                    ),
                    LoginUiState(
                        needsLogin = true,
                        versionName = "1.0.0 - 0",
                        enableLoginButton = true,
                        isLoading = true,
                        loginFormModel = LoginFormModel(
                            email = "teste@teste.com",
                            password = "password"
                        )
                    ),
                    LoginUiState(
                        needsLogin = true,
                        versionName = "1.0.0 - 0",
                        enableLoginButton = true,
                        isLoading = true,
                        simpleDialogParam = networkAlertDialogParam,
                        loginFormModel = LoginFormModel(
                            email = "teste@teste.com",
                            password = "password"
                        )
                    ),
                    LoginUiState(
                        needsLogin = true,
                        versionName = "1.0.0 - 0",
                        enableLoginButton = true,
                        simpleDialogParam = networkAlertDialogParam,
                        loginFormModel = LoginFormModel(
                            email = "teste@teste.com",
                            password = "password"
                        )
                    )
                ),
                collectedStates
            )
        }

//    private fun initViewModel() = LoginViewModel(
//        navigationManager = navigationManager,
//        buildConfigProvider = buildConfigProvider,
//        appThemeManager = mockk(),
//        authenticationUseCase = authenticationUseCase,
//        sendAnalyticsUseCase = mockk(relaxed = true),
//        sessionManager = sessionManager,
//        getBooleanRemoteConfigUseCase = mockk(relaxed = true)
//    )
}