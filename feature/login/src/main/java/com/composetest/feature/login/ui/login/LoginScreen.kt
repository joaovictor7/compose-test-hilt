package com.composetest.feature.login.ui.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
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
import com.composetest.core.designsystem.components.alertdialogs.DefaultAlertDialog
import com.composetest.core.designsystem.params.alertdialogs.DefaultAlertDialogParam
import com.composetest.core.designsystem.components.buttons.Button
import com.composetest.core.designsystem.components.cards.ElevatedCard
import com.composetest.core.designsystem.components.textfields.OutlinedTextField
import com.composetest.core.designsystem.enums.textfields.TextFieldIcons
import com.composetest.core.designsystem.params.textfields.TextFieldTrailingIconParam
import com.composetest.core.designsystem.compositions.LocalThemeProvider
import com.composetest.core.designsystem.dimensions.spacings
import com.composetest.core.designsystem.extensions.screenPadding
import com.composetest.core.designsystem.extensions.verticalTopBackgroundBrush
import com.composetest.core.designsystem.theme.ComposeTestTheme
import com.composetest.core.ui.interfaces.Command
import com.composetest.core.ui.interfaces.Screen
import com.composetest.feature.login.R

internal object LoginScreen : Screen<LoginUiState, LoginCommandReceiver> {

    @Composable
    override operator fun invoke(
        uiState: LoginUiState,
        onExecuteCommand: (Command<LoginCommandReceiver>) -> Unit
    ) {
        if (!uiState.needsLogin) return
        Box(
            modifier = Modifier
                .verticalTopBackgroundBrush()
                .screenPadding()
                .fillMaxSize()
        ) {
            ElevatedCard(modifier = Modifier.align(Alignment.Center)) {
                LoginForm(uiState = uiState, onExecuteCommand = onExecuteCommand)
            }
            Text(
                text = uiState.versionName,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = spacings.twelve)
            )
        }
        EffectsHandler(onExecuteCommand = onExecuteCommand)
        AlertDialogHandler(defaultAlertDialogParam = uiState.defaultAlertDialogParam)
    }
}

@Composable
private fun ColumnScope.LoginForm(
    uiState: LoginUiState,
    onExecuteCommand: (Command<LoginCommandReceiver>) -> Unit
) {
    Text(
        text = stringResource(R.string.feature_login_login),
        style = MaterialTheme.typography.headlineLarge,
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center
    )
    Spacer(Modifier.height(spacings.twelve))
    OutlinedTextField(
        labelText = stringResource(R.string.feature_login_email),
        textValue = uiState.loginFormModel.email,
        placeholderText = stringResource(R.string.feature_login_email_placeholder),
        supportingText = uiState.emailSupportingTextField?.let { stringResource(it) },
        imeAction = ImeAction.Next,
        trailingIconParam = uiState.emailTrailingIconTextField,
        modifier = Modifier.fillMaxWidth(),
        onFocusChanged = {
            if (!it.hasFocus) onExecuteCommand.invoke(LoginCommand.CheckShowInvalidEmailMsg)
        }
    ) { email ->
        onExecuteCommand(LoginCommand.WriteData(email = email))
    }
    Spacer(Modifier.height(spacings.sixteen))
    OutlinedTextField(
        textValue = uiState.loginFormModel.password,
        labelText = stringResource(R.string.feature_login_password),
        keyboardInput = KeyboardType.Password,
        trailingIconParam = TextFieldTrailingIconParam(
            iconType = TextFieldIcons.SEARCH
        ),
        modifier = Modifier.fillMaxWidth()
    ) { password ->
        onExecuteCommand(LoginCommand.WriteData(password = password))
    }
    Spacer(Modifier.height(spacings.sixteen))
    if (uiState.invalidCredentials) {
        Text(
            text = stringResource(R.string.feature_login_invalid_credentials),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.error
        )
        Spacer(Modifier.height(spacings.twelve))
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

@Composable
private fun EffectsHandler(onExecuteCommand: (Command<LoginCommandReceiver>) -> Unit) {
    val currentAppTheme = LocalThemeProvider.current
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
private fun AlertDialogHandler(defaultAlertDialogParam: DefaultAlertDialogParam?) =
    defaultAlertDialogParam?.let {
        DefaultAlertDialog(param = it)
    }

@Composable
@Preview
private fun Preview() {
    ComposeTestTheme {
        LoginScreen(
            LoginUiState(
                versionName = "Version",
                invalidCredentials = false,
                needsLogin = true
            )
        ) {}
    }
}