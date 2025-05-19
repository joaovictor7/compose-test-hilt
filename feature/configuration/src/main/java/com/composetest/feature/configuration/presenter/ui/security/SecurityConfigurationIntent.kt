package com.composetest.feature.configuration.presenter.ui.security

import com.composetest.core.ui.interfaces.Intent

internal sealed interface SecurityConfigurationIntent :
    Intent<SecurityConfigurationIntentReceiver> {

    data class ChangeSwitchBiometric(private val checked: Boolean) : SecurityConfigurationIntent {
        override fun execute(intentReceiver: SecurityConfigurationIntentReceiver) {
            intentReceiver.changeSwitchBiometric(checked)
        }
    }
}
