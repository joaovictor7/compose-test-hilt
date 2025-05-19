package com.composetest.feature.configuration.presenter.ui.configuration

import com.composetest.core.ui.interfaces.IntentReceiver
import com.composetest.feature.configuration.presenter.enums.Configuration

internal interface ConfigurationIntentReceiver : IntentReceiver<ConfigurationIntentReceiver> {

    fun configurationClick(configuration: Configuration)
}