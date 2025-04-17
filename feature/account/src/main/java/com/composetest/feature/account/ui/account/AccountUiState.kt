package com.composetest.feature.account.ui.account

import com.composetest.core.designsystem.enums.buttons.LoadingButtonState
import com.composetest.core.designsystem.params.alertdialogs.SimpleDialogParam
import com.composetest.feature.account.models.AccountScreenModel

internal data class AccountUiState(
    val accountScreenModels: List<AccountScreenModel> = emptyList(),
    val loadingState: LoadingButtonState = LoadingButtonState.IDLE,
) {
    fun setAccountScreenModels(models: List<AccountScreenModel>) =
        copy(accountScreenModels = models)
    fun setLoadingState(state: LoadingButtonState) = copy(loadingState = state)
}