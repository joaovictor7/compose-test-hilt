package com.composetest.feature.login.ui.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.composetest.core.designsystem.components.buttons.Button
import com.composetest.core.designsystem.components.dialogs.SimpleDialog
import com.composetest.core.designsystem.components.textfields.OutlinedTextField
import com.composetest.core.designsystem.compositions.LocalTheme
import com.composetest.core.designsystem.constants.screenMargin
import com.composetest.core.designsystem.dimensions.Spacing
import com.composetest.core.designsystem.extensions.screenMarginWithoutBar
import com.composetest.core.designsystem.extensions.verticalTopBackgroundBrush
import com.composetest.core.designsystem.theme.ComposeTestTheme
import com.composetest.core.ui.interfaces.Command
import com.composetest.core.ui.interfaces.Screen
import com.composetest.feature.login.R
import kotlinx.coroutines.flow.Flow

internal object LoginScreen : Screen<LoginUiState, LoginUiEvent, LoginCommandReceiver> {

    @Composable
    override operator fun invoke(
        uiState: LoginUiState,
        uiEvent: Flow<LoginUiEvent>?,
        onExecuteCommand: (Command<LoginCommandReceiver>) -> Unit
    ) {
        if (!uiState.needsLogin) return
        Box(
            modifier = Modifier
                .verticalTopBackgroundBrush()
                .screenMarginWithoutBar()
                .fillMaxSize()
        ) {
            ElevatedCard(modifier = Modifier.align(Alignment.Center)) {
                LoginForm(uiState = uiState, onExecuteCommand = onExecuteCommand)
            }
            VersionName(uiState = uiState)
        }
        EffectsHandler(onExecuteCommand = onExecuteCommand)
        HandleDialogs(uiState = uiState, onExecuteCommand = onExecuteCommand)
    }
}

@Composable
private fun LoginForm(
    uiState: LoginUiState,
    onExecuteCommand: (Command<LoginCommandReceiver>) -> Unit
) {
    Column(modifier = Modifier.padding(screenMargin)) {
        Text(
            text = stringResource(R.string.feature_login_login),
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.height(Spacing.twelve))
        Column(verticalArrangement = Arrangement.spacedBy(Spacing.sixteen)) {
            OutlinedTextField(
                labelText = stringResource(R.string.feature_login_email),
                textValue = uiState.loginFormModel.email,
                placeholderText = stringResource(R.string.feature_login_email_placeholder),
                supportingText = uiState.emailSupporting?.let { stringResource(it) },
                imeAction = ImeAction.Next,
                trailingIcon = uiState.emailTrailingIcon,
                modifier = Modifier.fillMaxWidth(),
                onFocusChanged = {
                    onExecuteCommand(LoginCommand.CheckShowInvalidEmailMsg(it.hasFocus))
                }
            ) { email ->
                onExecuteCommand(LoginCommand.WriteData(email = email))
            }
            OutlinedTextField(
                textValue = uiState.loginFormModel.password,
                labelText = stringResource(R.string.feature_login_password),
                keyboardInput = KeyboardType.Password,
                modifier = Modifier.fillMaxWidth()
            ) { password ->
                onExecuteCommand(LoginCommand.WriteData(password = password))
            }
            if (uiState.invalidCredentials) {
                Text(
                    text = stringResource(R.string.feature_login_invalid_credentials),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.error
                )
                Spacer(Modifier.height(Spacing.twelve))
            }
            if (!uiState.isLoading) {
                Button(
                    text = stringResource(R.string.feature_login_enter),
                    modifier = Modifier.fillMaxWidth(),
                    enabled = uiState.enableLoginButton
                ) { onExecuteCommand(LoginCommand.Login) }
            } else {
                CircularProgressIndicator(
                    modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
                )
            }
        }
    }
}

@Composable
private fun BoxScope.VersionName(uiState: LoginUiState) {
    Column(
        modifier = Modifier
            .align(Alignment.BottomCenter)
            .padding(bottom = Spacing.twelve),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = uiState.versionName,
            style = MaterialTheme.typography.bodyMedium
        )
        uiState.distributionTextId?.let {
            Text(
                text = stringResource(it),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
private fun EffectsHandler(onExecuteCommand: (Command<LoginCommandReceiver>) -> Unit) {
    val currentAppTheme = LocalTheme.current
    LaunchedEffect(Unit) {
        onExecuteCommand(LoginCommand.SetCustomTheme(true, currentAppTheme))
    }
    DisposableEffect(Unit) {
        onDispose {
            onExecuteCommand(LoginCommand.SetCustomTheme(false, currentAppTheme))
        }
    }
}

@Composable
private fun HandleDialogs(
    uiState: LoginUiState,
    onExecuteCommand: (Command<LoginCommandReceiver>) -> Unit
) = uiState.simpleDialogParam?.let {
    SimpleDialog(param = it) {
        onExecuteCommand(LoginCommand.DismissSimpleDialog)
    }
}

@Composable
@Preview
private fun Preview() {
    ComposeTestTheme {
        LoginScreen(
            LoginUiState(
                versionName = "Version",
                distributionTextId = R.string.feature_login_full_distribution,
                invalidCredentials = false,
                needsLogin = true
            ),
            uiEvent = null
        ) {}
    }
}