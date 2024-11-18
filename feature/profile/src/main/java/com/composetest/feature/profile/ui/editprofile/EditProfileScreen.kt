package com.composetest.feature.profile.ui.editprofile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.composetest.core.designsystem.components.buttons.Button
import com.composetest.core.designsystem.components.textfields.TextField
import com.composetest.core.designsystem.dimensions.spacings
import com.composetest.core.designsystem.extensions.screenMargin
import com.composetest.core.designsystem.theme.ComposeTestTheme
import com.composetest.core.ui.interfaces.Command
import com.composetest.core.ui.interfaces.Screen
import com.composetest.feature.profile.models.ProfileFormModel

internal object EditProfileScreen : Screen<EditProfileUiState, EditProfileCommandReceiver> {

    @Composable
    override operator fun invoke(
        uiState: EditProfileUiState,
        onExecuteCommand: (Command<EditProfileCommandReceiver>) -> Unit
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .screenMargin()
                .imePadding(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            EditProfileFormData(uiState = uiState, onExecuteCommand = onExecuteCommand)
            Button(
                modifier = Modifier
                    .padding(top = spacings.twelve)
                    .fillMaxWidth(0.9f)
                    .align(Alignment.CenterHorizontally),
                text = "Salvar",
                enabled = uiState.saveButtonEnabled
            ) {
                onExecuteCommand(EditProfileCommand.SaveProfile)
            }
        }
    }
}

@Composable
private fun EditProfileFormData(
    uiState: EditProfileUiState,
    onExecuteCommand: (Command<EditProfileCommandReceiver>) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(spacings.twelve)) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            labelText = "E-mail",
            textValue = uiState.profileForm?.email.orEmpty()
        ) {
            onExecuteCommand(EditProfileCommand.SetFormData(ProfileFormModel(email = it)))
        }
        TextField(
            modifier = Modifier.fillMaxWidth(),
            labelText = "Nome",
            textValue = uiState.profileForm?.name.orEmpty()
        ) {
            onExecuteCommand(EditProfileCommand.SetFormData(ProfileFormModel(name = it)))
        }
    }
}

@Composable
@Preview
private fun Preview() {
    ComposeTestTheme {
        EditProfileFormData(
            EditProfileUiState()
        ) {}
    }
}