package com.composetest.feature.account.presentation.ui.account

import com.composetest.core.ui.interfaces.IntentReceiver
import com.composetest.feature.account.presentation.enums.AccountDataRow

internal interface AccountIntentReceiver : IntentReceiver<AccountIntentReceiver> {
    fun updateFormData(dataRowId: AccountDataRow, value: String)
    fun saveData()
    fun backHandler()
}