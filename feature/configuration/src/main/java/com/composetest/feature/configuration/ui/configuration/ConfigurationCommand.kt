package com.composetest.feature.configuration.ui.configuration

import com.composetest.core.ui.interfaces.Command
import com.composetest.feature.configuration.enums.Configuration

internal sealed interface ConfigurationCommand : Command<ConfigurationCommandReceiver> {
    data class ClickConfiguration(private val configuration: Configuration) : ConfigurationCommand {
        override fun execute(commandReceiver: ConfigurationCommandReceiver) {
            commandReceiver.clickConfiguration(configuration)
        }
    }
}
