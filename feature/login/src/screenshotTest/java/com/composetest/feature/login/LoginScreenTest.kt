package com.composetest.feature.login

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.composetest.core.designsystem.theme.ComposeTestTheme
import com.composetest.feature.login.presenter.model.BiometricModel
import com.composetest.feature.login.presenter.ui.login.LoginScreen
import com.composetest.feature.login.presenter.ui.login.LoginUiState

internal class LoginScreenTest {
    @Composable
    @PreviewLightDark
    private fun Preview() {
        ComposeTestTheme {
            LoginScreen(
                LoginUiState(
                    versionName = "Version",
                    invalidCredentials = true,
                    biometricModel = BiometricModel(
                        messageId = R.string.feature_login_biometric_is_blocked
                    )
                ),
            )
        }
    }
}