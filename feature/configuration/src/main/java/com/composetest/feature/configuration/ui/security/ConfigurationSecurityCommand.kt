package com.composetest.feature.configuration.ui.security

import com.composetest.core.ui.interfaces.Command

internal sealed interface ConfigurationSecurityCommand :
    Command<ConfigurationSecurityCommandReceiver> {

    data class ChangeSwitchBiometric(private val checked: Boolean) : ConfigurationSecurityCommand {
        override fun execute(commandReceiver: ConfigurationSecurityCommandReceiver) {
            commandReceiver.changeSwitchBiometric(checked)
        }
    }
}
