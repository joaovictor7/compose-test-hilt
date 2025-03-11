package com.composetest.feature.configuration.presenter.ui.theme

import com.composetest.core.ui.interfaces.Command
import com.composetest.feature.configuration.presenter.enums.ThemeConfiguration

internal sealed interface ThemeConfigurationCommand : Command<ThemeConfigurationCommandReceiver> {

    data class ChangeThemeConfiguration(
        private val selectedTheme: ThemeConfiguration
    ) : ThemeConfigurationCommand {
        override fun execute(commandReceiver: ThemeConfigurationCommandReceiver) {
            commandReceiver.changeTheme(selectedTheme)
        }
    }

    data class ChangeDynamicColor(private val active: Boolean) : ThemeConfigurationCommand {
        override fun execute(commandReceiver: ThemeConfigurationCommandReceiver) {
            commandReceiver.changeDynamicColor(active)
        }
    }
}
