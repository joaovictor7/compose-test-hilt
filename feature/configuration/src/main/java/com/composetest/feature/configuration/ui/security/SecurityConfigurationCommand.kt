package com.composetest.feature.configuration.ui.security

import com.composetest.core.ui.interfaces.Command

internal sealed interface SecurityConfigurationCommand :
    Command<SecurityConfigurationCommandReceiver> {

    data class ChangeSwitchBiometric(private val checked: Boolean) : SecurityConfigurationCommand {
        override fun execute(commandReceiver: SecurityConfigurationCommandReceiver) {
            commandReceiver.changeSwitchBiometric(checked)
        }
    }
}
