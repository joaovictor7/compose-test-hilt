package com.composetest.feature.configuration.ui.security

import com.composetest.core.ui.interfaces.CommandReceiver

internal interface SecurityConfigurationCommandReceiver :
    CommandReceiver<SecurityConfigurationCommandReceiver> {

    fun changeSwitchBiometric(checked: Boolean)
}