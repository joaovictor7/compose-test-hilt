package com.composetest.feature.configuration.presenter.ui.configuration

import com.composetest.core.ui.interfaces.Intent
import com.composetest.feature.configuration.presenter.enums.Configuration

internal sealed interface ConfigurationIntent : Intent<ConfigurationIntentReceiver> {
    data class ConfigurationClick(private val configuration: Configuration) : ConfigurationIntent {
        override fun execute(intentReceiver: ConfigurationIntentReceiver) {
            intentReceiver.configurationClick(configuration)
        }
    }
}
