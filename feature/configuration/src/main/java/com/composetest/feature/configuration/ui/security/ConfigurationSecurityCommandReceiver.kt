package com.composetest.feature.configuration.ui.security

import com.composetest.core.ui.interfaces.CommandReceiver

internal interface ConfigurationSecurityCommandReceiver :
    CommandReceiver<ConfigurationSecurityCommandReceiver> {

    fun changeSwitchBiometric(checked: Boolean)
}