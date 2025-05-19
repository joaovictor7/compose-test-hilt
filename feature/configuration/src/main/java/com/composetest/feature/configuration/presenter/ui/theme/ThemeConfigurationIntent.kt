package com.composetest.feature.configuration.presenter.ui.theme

import com.composetest.core.ui.interfaces.Intent
import com.composetest.feature.configuration.presenter.enums.ThemeConfiguration

internal sealed interface ThemeConfigurationIntent : Intent<ThemeConfigurationIntentReceiver> {

    data class ChangeThemeConfiguration(
        private val selectedTheme: ThemeConfiguration
    ) : ThemeConfigurationIntent {
        override fun execute(intentReceiver: ThemeConfigurationIntentReceiver) {
            intentReceiver.changeTheme(selectedTheme)
        }
    }

    data class ChangeDynamicColor(private val active: Boolean) : ThemeConfigurationIntent {
        override fun execute(intentReceiver: ThemeConfigurationIntentReceiver) {
            intentReceiver.changeDynamicColor(active)
        }
    }
}
