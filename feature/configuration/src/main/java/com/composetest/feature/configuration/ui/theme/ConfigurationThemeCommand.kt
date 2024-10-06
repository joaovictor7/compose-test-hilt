package com.composetest.feature.configuration.ui.theme

import com.composetest.core.ui.interfaces.Command
import com.composetest.feature.configuration.enums.ThemeConfiguration

internal sealed interface ConfigurationThemeCommand : Command<ConfigurationThemeCommandReceiver> {

    data object NavigateBack: ConfigurationThemeCommand {
        override fun execute(commandReceiver: ConfigurationThemeCommandReceiver) {
            commandReceiver.navigateBack()
        }
    }

    data class ChangeTheme(
        private val selectedTheme: ThemeConfiguration
    ) : ConfigurationThemeCommand {
        override fun execute(commandReceiver: ConfigurationThemeCommandReceiver) {
            commandReceiver.changeTheme(selectedTheme)
        }
    }

    data class ChangeDynamicColor(private val active: Boolean) : ConfigurationThemeCommand {
        override fun execute(commandReceiver: ConfigurationThemeCommandReceiver) {
            commandReceiver.changeDynamicColor(active)
        }
    }
}
