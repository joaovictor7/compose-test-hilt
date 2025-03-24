package com.composetest.feature.account.ui.account

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.composetest.core.designsystem.components.buttons.LoadingButton
import com.composetest.core.designsystem.components.dialogs.SimpleDialog
import com.composetest.core.designsystem.components.scaffolds.ScreenScaffold
import com.composetest.core.designsystem.components.textfields.TextField
import com.composetest.core.designsystem.components.topbar.LeftTopBar
import com.composetest.core.designsystem.constants.screenMargin
import com.composetest.core.designsystem.dimensions.Spacing
import com.composetest.core.designsystem.extensions.horizontalScreenMargin
import com.composetest.core.designsystem.theme.ComposeTestTheme
import com.composetest.core.router.extensions.navigateBack
import com.composetest.core.ui.interfaces.Command
import com.composetest.feature.account.enums.AccountDataRow
import com.composetest.feature.account.models.AccountScreenModel
import com.composetest.feature.profile.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
internal fun AccountScreen(
    uiState: AccountUiState,
    uiEvent: Flow<AccountUiEvent> = emptyFlow(),
    onExecuteCommand: (Command<AccountCommandReceiver>) -> Unit = {},
    navController: NavHostController = rememberNavController(),
) {
    LaunchedEffectsHandler(uiEvent = uiEvent, navController = navController)
    DialogHandler(uiState = uiState, onExecuteCommand = onExecuteCommand)
    BackHandler { onExecuteCommand(AccountCommand.BackHandler) }
    ScreenScaffold(
        modifier = Modifier
            .horizontalScreenMargin()
            .padding(bottom = screenMargin),
        topBar = { LeftTopBar(titleId = R.string.account_title) }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(Spacing.twentyFour)) {
                uiState.accountScreenModels.forEach { data ->
                    TextField(
                        modifier = Modifier.fillMaxWidth(),
                        labelText = stringResource(data.labelTextId),
                        textValue = data.text,
                        placeholderText = data.placeholder,
                        keyboardInput = data.keyboardType,
                    ) {
                        onExecuteCommand(AccountCommand.UpdateFormData(data.id, it))
                    }
                }
                LoadingButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(R.string.account_update_button),
                    loadingState = uiState.loadingState,
                ) {
                    onExecuteCommand(AccountCommand.SaveData)
                }
            }
        }
    }
}

@Composable
private fun DialogHandler(
    uiState: AccountUiState,
    onExecuteCommand: (Command<AccountCommandReceiver>) -> Unit
) = uiState.simpleDialogParam?.let {
    SimpleDialog(param = it) {
        onExecuteCommand(AccountCommand.DismissSimpleDialog)
    }
}

@Composable
private fun LaunchedEffectsHandler(
    uiEvent: Flow<AccountUiEvent>,
    navController: NavHostController,
) {
    LaunchedEffect(Unit) {
        uiEvent.collect {
            when (it) {
                is AccountUiEvent.NavigateBack -> {
                    navController.navigateBack(it.result)
                }
            }
        }
    }
}

@Composable
@Preview
private fun Preview() {
    ComposeTestTheme {
        AccountScreen(
            AccountUiState(
                accountScreenModels = listOf(
                    AccountScreenModel(
                        id = AccountDataRow.EMAIL,
                        labelTextId = R.string.account_email_title,
                        text = "E-mail"
                    ),
                    AccountScreenModel(
                        id = AccountDataRow.EMAIL,
                        labelTextId = R.string.account_email_title,
                        text = "E-mail"
                    ),
                )
            ),
        )
    }
}