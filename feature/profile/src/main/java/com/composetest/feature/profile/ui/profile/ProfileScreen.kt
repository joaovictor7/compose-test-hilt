package com.composetest.feature.profile.ui.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import com.composetest.core.designsystem.components.labels.DataLabel
import com.composetest.core.designsystem.components.toolbar.Toolbar
import com.composetest.core.designsystem.dimensions.spacings
import com.composetest.core.designsystem.enums.toolbar.ToolbarAction
import com.composetest.core.designsystem.params.toolbar.ToolbarActionParam
import com.composetest.core.designsystem.theme.ComposeTestTheme
import com.composetest.core.ui.interfaces.Command
import com.composetest.core.ui.interfaces.Screen
import com.composetest.feature.profile.R
import com.composetest.feature.profile.models.ProfileScreenModel
import kotlinx.coroutines.flow.Flow

internal object ProfileScreen : Screen<ProfileUiState, ProfileUiEvent, ProfileCommandReceiver> {

    @Composable
    override operator fun invoke(
        uiState: ProfileUiState,
        uiEvent: Flow<ProfileUiEvent>?,
        onExecuteCommand: (Command<ProfileCommandReceiver>) -> Unit
    ) {
        Toolbar(
            titleId = R.string.profile_title,
            actions = listOf(
                ToolbarActionParam(ToolbarAction.EDIT) {
                    onExecuteCommand(ProfileCommand.NavigateToEditProfile)
                }
            )
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(spacings.eight)) {
                uiState.profileScreenModels.forEach {
                    DataLabel(
                        labelTitleId = it.titleId,
                        labelText = it.text
                    )
                }
            }
        }
    }
}

@Composable
@Preview
private fun Preview() {
    ComposeTestTheme {
        ProfileScreen(
            ProfileUiState(
                profileScreenModels = listOf(
                    ProfileScreenModel(
                        titleId = R.string.profile_email_title,
                        text = AnnotatedString("E-mail")
                    ),
                    ProfileScreenModel(
                        titleId = R.string.profile_email_title,
                        text = AnnotatedString("E-mail")
                    )
                )
            ),
            uiEvent = null
        ) {}
    }
}