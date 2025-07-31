package com.composetest.feature.login.presenter.ui.login

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.android.tools.screenshot.PreviewTest
import com.composetest.core.designsystem.theme.ComposeTestTheme
import com.composetest.feature.login.R
import com.composetest.feature.login.presenter.model.BiometricModel
import com.composetest.feature.login.presenter.ui.login.viewmodel.LoginUiState

@PreviewTest
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
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