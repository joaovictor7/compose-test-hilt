package com.composetest.feature.profile.ui.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.composetest.core.designsystem.dimensions.spacings
import com.composetest.core.designsystem.extensions.screenPadding
import com.composetest.core.designsystem.theme.ComposeTestTheme
import com.composetest.core.ui.interfaces.Command
import com.composetest.core.ui.interfaces.Screen
import com.composetest.feature.profile.models.ProfileFormModel

internal object ProfileScreen : Screen<ProfileUiState, ProfileCommandReceiver> {

    @Composable
    override operator fun invoke(
        uiState: ProfileUiState,
        onExecuteCommand: (Command<ProfileCommandReceiver>) -> Unit
    ) {
        Column(
            modifier = Modifier.screenPadding(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            ProfileDataRow(title = "Nome", text = uiState.profileForm?.name)
            ProfileDataRow(title = "E-mail", text = uiState.profileForm?.email)
        }
    }
}

@Composable
private fun ProfileDataRow(
    title: String,
    text: String?
) {
    Column(modifier = Modifier.padding(horizontal = spacings.eight)) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall
        )
        Text(
            text = text.orEmpty(),
            style = MaterialTheme.typography.titleMedium
        )
    }
    Spacer(Modifier.height(spacings.eight))
    HorizontalDivider()
}

@Composable
@Preview
private fun Preview() {
    ComposeTestTheme {
        ProfileScreen(
            ProfileUiState(
                profileForm = ProfileFormModel(
                    name = "Nome teste",
                    email = "E-mail teste"
                )
            )
        ) {}
    }
}