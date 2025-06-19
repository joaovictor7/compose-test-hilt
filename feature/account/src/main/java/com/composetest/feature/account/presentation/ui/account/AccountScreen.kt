package com.composetest.feature.account.presentation.ui.account

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.composetest.core.designsystem.component.button.LoadingButton
import com.composetest.core.designsystem.component.scaffold.ScreenScaffold
import com.composetest.core.designsystem.component.textfield.TextField
import com.composetest.core.designsystem.component.topbar.LeftTopBar
import com.composetest.core.designsystem.dimension.Spacing
import com.composetest.core.designsystem.dimension.screenMargin
import com.composetest.core.designsystem.extension.horizontalScreenMargin
import com.composetest.core.designsystem.theme.ComposeTestTheme
import com.composetest.core.router.extension.navigateBack
import com.composetest.core.router.extension.navigateTo
import com.composetest.core.ui.interfaces.Intent
import com.composetest.core.ui.util.UiEventsObserver
import com.composetest.feature.account.presentation.enums.AccountDataRow
import com.composetest.feature.account.presentation.model.AccountScreenModel
import com.composetest.feature.profile.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
internal fun AccountScreen(
    uiState: AccountUiState,
    uiEvent: Flow<AccountUiEvent> = emptyFlow(),
    onExecuteIntent: (Intent<AccountIntentReceiver>) -> Unit = {},
    navController: NavHostController = rememberNavController(),
) {
    UiEventHandler(uiEvent = uiEvent, navController = navController)
    BackHandler { onExecuteIntent(AccountIntent.BackHandler) }
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
            Column(verticalArrangement = Arrangement.spacedBy(Spacing.extraLarge)) {
                uiState.accountScreenModels.forEach { data ->
                    TextField(
                        modifier = Modifier.fillMaxWidth(),
                        labelText = stringResource(data.labelTextId),
                        textValue = data.text,
                        placeholderText = data.placeholder,
                        keyboardInput = data.keyboardType,
                    ) {
                        onExecuteIntent(AccountIntent.UpdateFormData(data.id, it))
                    }
                }
                LoadingButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(R.string.account_update_button),
                    loadingState = uiState.loadingState,
                ) {
                    onExecuteIntent(AccountIntent.SaveData)
                }
            }
        }
    }
}

@Composable
private fun UiEventHandler(
    uiEvent: Flow<AccountUiEvent>,
    navController: NavHostController,
) {
    UiEventsObserver(uiEvent) {
        when (it) {
            is AccountUiEvent.NavigateBack -> {
                navController.navigateBack(it.result)
            }
            is AccountUiEvent.NavigateTo -> {
                navController.navigateTo(it.navigationModel)
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