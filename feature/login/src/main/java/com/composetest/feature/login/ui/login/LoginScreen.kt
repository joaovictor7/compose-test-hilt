package com.composetest.feature.login.ui.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.composetest.core.designsystem.R
import com.composetest.core.designsystem.components.buttons.Button
import com.composetest.core.designsystem.components.dialogs.SimpleDialog
import com.composetest.core.designsystem.components.icons.VibratingIcon
import com.composetest.core.designsystem.components.textfields.OutlinedTextField
import com.composetest.core.designsystem.compositions.LocalTheme
import com.composetest.core.designsystem.constants.screenMargin
import com.composetest.core.designsystem.dimensions.Spacing
import com.composetest.core.designsystem.extensions.opacity
import com.composetest.core.designsystem.extensions.screenMarginWithoutBar
import com.composetest.core.designsystem.extensions.verticalTopBackgroundBrush
import com.composetest.core.designsystem.theme.ComposeTestTheme
import com.composetest.core.security.utils.showBiometricPrompt
import com.composetest.core.ui.interfaces.Command
import com.composetest.core.ui.interfaces.Screen
import com.composetest.feature.login.models.BiometricModel
import kotlinx.coroutines.flow.Flow
import com.composetest.feature.login.R as LoginResources

private const val AMOUNT_VIBRATION = 2
private const val DURATION = 50
private const val DISPLACEMENT = 15f

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
        EffectsHandler(loginUiEvent = uiEvent, onExecuteCommand = onExecuteCommand)
        HandleDialogs(uiState = uiState, onExecuteCommand = onExecuteCommand)
    }
}

@Composable
private fun LoginForm(
    uiState: LoginUiState,
    onExecuteCommand: (Command<LoginCommandReceiver>) -> Unit
) {
    Column(
        modifier = Modifier.padding(screenMargin),
        verticalArrangement = Arrangement.spacedBy(Spacing.twelve)
    ) {
        Text(
            text = stringResource(LoginResources.string.feature_login_login),
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Column(verticalArrangement = Arrangement.spacedBy(Spacing.sixteen)) {
            OutlinedTextField(
                labelText = stringResource(LoginResources.string.feature_login_email),
                textValue = uiState.loginFormModel.email,
                placeholderText = stringResource(LoginResources.string.feature_login_email_placeholder),
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
                labelText = stringResource(LoginResources.string.feature_login_password),
                keyboardInput = KeyboardType.Password,
                modifier = Modifier.fillMaxWidth()
            ) { password ->
                onExecuteCommand(LoginCommand.WriteData(password = password))
            }
            if (uiState.invalidCredentials) {
                Text(
                    text = stringResource(LoginResources.string.feature_login_invalid_credentials),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.error
                )
            }
            if (!uiState.isLoading) {
                ButtonsArea(uiState = uiState, onExecuteCommand = onExecuteCommand)
            } else {
                CircularProgressIndicator(
                    modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
                )
            }
        }
    }
}

@Composable
private fun ButtonsArea(
    uiState: LoginUiState,
    onExecuteCommand: (Command<LoginCommandReceiver>) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(Spacing.eight)
    ) {
        Button(
            text = stringResource(LoginResources.string.feature_login_enter),
            modifier = Modifier.fillMaxWidth(),
            enabled = uiState.loginButtonIsEnabled
        ) { onExecuteCommand(LoginCommand.Login) }
        BiometricButton(uiState = uiState, onExecuteCommand = onExecuteCommand)
    }
}

@Composable
private fun BiometricButton(
    uiState: LoginUiState,
    onExecuteCommand: (Command<LoginCommandReceiver>) -> Unit
) = uiState.biometricModel?.let { biometric ->
    VibratingIcon(
        modifier = Modifier
            .clip(MaterialTheme.shapes.large)
            .setBiometricButtonClick(biometric.isAvailable) {
                onExecuteCommand(LoginCommand.ShowBiometricPrompt)
            }
            .padding(Spacing.four),
        iconId = R.drawable.ic_fingerprint_extra_large,
        iconTint = when {
            !biometric.isAvailable -> MaterialTheme.colorScheme.onSurface.opacity(0.38f)
            biometric.isError -> MaterialTheme.colorScheme.error
            else -> null
        },
        amountVibrations = if (biometric.isError) AMOUNT_VIBRATION else 0,
        duration = DURATION,
        displacement = DISPLACEMENT
    ) {
        onExecuteCommand(LoginCommand.BiometricErrorAnimationFinished)
    }
    biometric.messageId?.let {
        Text(
            text = stringResource(it),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.error
        )
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
    }
}

@Composable
private fun Modifier.setBiometricButtonClick(isAvailable: Boolean, onClick: () -> Unit) = also {
    if (isAvailable) {
        return clickable(onClick = onClick)
    }
}

@Composable
private fun EffectsHandler(
    loginUiEvent: Flow<LoginUiEvent>?,
    onExecuteCommand: (Command<LoginCommandReceiver>) -> Unit
) {
    val context = LocalContext.current
    val currentAppTheme = LocalTheme.current
    stringResource(LoginResources.string.feature_login_biometric_title)
    LaunchedEffect(Unit) {
        onExecuteCommand(LoginCommand.SetCustomTheme(true, currentAppTheme))
    }
    LaunchedEffect(loginUiEvent) {
        loginUiEvent?.collect {
            when (it) {
                is LoginUiEvent.ShowBiometricPrompt -> showBiometricPrompt(
                    context = context,
                    titleId = LoginResources.string.feature_login_biometric_title,
                    subtitleId = LoginResources.string.feature_login_biometric_subtitle,
                    onSuccess = { onExecuteCommand(LoginCommand.Login) },
                    onError = { error ->
                        onExecuteCommand(LoginCommand.BiometricErrorHandler(error))
                    }
                )
            }
        }
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
                invalidCredentials = true,
                needsLogin = true,
                biometricModel = BiometricModel(
                    messageId = LoginResources.string.feature_login_biometric_is_blocked
                )
            ),
            uiEvent = null
        ) {}
    }
}