package com.composetest.feature.configuration.presenter.ui.security

import com.composetest.core.ui.interfaces.CommandReceiver

internal interface SecurityConfigurationCommandReceiver :
    CommandReceiver<SecurityConfigurationCommandReceiver> {

    fun changeSwitchBiometric(checked: Boolean)
}