package com.composetest.feature.account.presentation.ui.account

import com.composetest.core.designsystem.enums.button.LoadingButtonState
import com.composetest.feature.account.presentation.model.AccountScreenModel

internal data class AccountUiState(
    val accountScreenModels: List<AccountScreenModel> = emptyList(),
    val loadingState: LoadingButtonState = LoadingButtonState.IDLE,
) {
    fun setAccountScreenModels(models: List<AccountScreenModel>) =
        copy(accountScreenModels = models)

    fun setLoadingState(state: LoadingButtonState) = copy(loadingState = state)
}