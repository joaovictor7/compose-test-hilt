package com.composetest.feature.configuration.ui.configuration

import com.composetest.core.ui.interfaces.CommandReceiver
import com.composetest.feature.configuration.enums.Configuration

internal interface ConfigurationCommandReceiver : CommandReceiver<ConfigurationCommandReceiver> {

    fun configurationClick(configuration: Configuration)
}