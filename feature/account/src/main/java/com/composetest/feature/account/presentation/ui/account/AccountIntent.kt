package com.composetest.feature.account.presentation.ui.account

import com.composetest.core.ui.interfaces.Intent
import com.composetest.feature.account.presentation.enums.AccountDataRow

internal sealed interface AccountIntent : Intent<AccountIntentReceiver> {

    data class UpdateFormData(
        private val dataRowId: AccountDataRow,
        private val value: String
    ) : AccountIntent {
        override fun execute(intentReceiver: AccountIntentReceiver) {
            intentReceiver.updateFormData(dataRowId, value)
        }
    }

    data object SaveData : AccountIntent {
        override fun execute(intentReceiver: AccountIntentReceiver) {
            intentReceiver.saveData()
        }
    }

    data object BackHandler : AccountIntent {
        override fun execute(intentReceiver: AccountIntentReceiver) {
            intentReceiver.backHandler()
        }
    }
}