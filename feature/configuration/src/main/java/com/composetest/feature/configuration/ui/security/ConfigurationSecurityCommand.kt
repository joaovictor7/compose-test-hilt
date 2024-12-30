package com.composetest.feature.configuration.ui.security

import com.composetest.core.ui.interfaces.Command

internal sealed interface ConfigurationSecurityCommand :
    Command<ConfigurationSecurityCommandReceiver> {

}
