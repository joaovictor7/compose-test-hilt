package com.composetest.feature.account.presentation.ui.account

import com.composetest.core.ui.interfaces.Command
import com.composetest.feature.account.presentation.enums.AccountDataRow

internal sealed interface AccountCommand : Command<AccountCommandReceiver> {

    data class UpdateFormData(
        private val dataRowId: AccountDataRow,
        private val value: String
    ) : AccountCommand {
        override fun execute(commandReceiver: AccountCommandReceiver) {
            commandReceiver.updateFormData(dataRowId, value)
        }
    }

    data object SaveData : AccountCommand {
        override fun execute(commandReceiver: AccountCommandReceiver) {
            commandReceiver.saveData()
        }
    }

    data object BackHandler : AccountCommand {
        override fun execute(commandReceiver: AccountCommandReceiver) {
            commandReceiver.backHandler()
        }
    }
}