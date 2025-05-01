package com.composetest.feature.account.ui.account

import com.composetest.core.ui.interfaces.CommandReceiver
import com.composetest.feature.account.enums.AccountDataRow

internal interface AccountCommandReceiver : CommandReceiver<AccountCommandReceiver> {
    fun updateFormData(dataRowId: AccountDataRow, value: String)
    fun saveData()
    fun backHandler()
}