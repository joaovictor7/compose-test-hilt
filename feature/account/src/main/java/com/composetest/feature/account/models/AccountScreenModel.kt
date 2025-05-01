package com.composetest.feature.account.models

import androidx.annotation.StringRes
import androidx.compose.ui.text.input.KeyboardType
import com.composetest.feature.account.enums.AccountDataRow

internal data class AccountScreenModel(
    val id: AccountDataRow,
    @StringRes val labelTextId: Int,
    val text: String = String(),
    val placeholder: String? = null,
    val keyboardType: KeyboardType = KeyboardType.Text
)