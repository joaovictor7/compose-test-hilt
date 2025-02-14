package com.composetest.feature.profile.ui.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.composetest.core.designsystem.components.labels.DataLabel
import com.composetest.core.designsystem.components.scaffolds.ScreenScaffold
import com.composetest.core.designsystem.components.topbar.LeftTopBar
import com.composetest.core.designsystem.dimensions.Spacing
import com.composetest.core.designsystem.enums.topbar.TopBarAction
import com.composetest.core.designsystem.theme.ComposeTestTheme
import com.composetest.core.router.extensions.navigateTo
import com.composetest.core.ui.interfaces.Command
import com.composetest.feature.profile.R
import com.composetest.feature.profile.models.ProfileScreenModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
internal fun ProfileScreen(
    uiState: ProfileUiState,
    uiEvent: Flow<ProfileUiEvent> = emptyFlow(),
    onExecuteCommand: (Command<ProfileCommandReceiver>) -> Unit = {},
    navController: NavHostController = rememberNavController()
) {
    LaunchedEffectHandler(uiEvent = uiEvent, navController = navController)
    ScreenScaffold(
        topBar = {
            LeftTopBar(
                titleId = R.string.profile_title,
                actionIcons = listOf(TopBarAction.EDIT),
                onClickAction = { onExecuteCommand(ProfileCommand.ToolbarActionClick(it)) },
            )
        }
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(Spacing.eight)) {
            uiState.profileScreenModels.forEach {
                DataLabel(
                    labelTitleId = it.titleId,
                    labelText = it.text
                )
            }
        }
    }
}

@Composable
private fun LaunchedEffectHandler(
    uiEvent: Flow<ProfileUiEvent>,
    navController: NavHostController
) {
    LaunchedEffect(Unit) {
        uiEvent.collect {
            when (it) {
                is ProfileUiEvent.NavigateTo -> navController.navigateTo(it.navigationModel)
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
        )
    }
}