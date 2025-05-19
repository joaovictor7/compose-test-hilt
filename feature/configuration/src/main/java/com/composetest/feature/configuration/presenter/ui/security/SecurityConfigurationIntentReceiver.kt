package com.composetest.feature.configuration.presenter.ui.security

import com.composetest.core.ui.interfaces.IntentReceiver

internal interface SecurityConfigurationIntentReceiver :
    IntentReceiver<SecurityConfigurationIntentReceiver> {

    fun changeSwitchBiometric(checked: Boolean)
}