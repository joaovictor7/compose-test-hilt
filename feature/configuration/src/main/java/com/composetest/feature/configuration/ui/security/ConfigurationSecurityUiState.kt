package com.composetest.feature.configuration.ui.security

import com.composetest.common.extensions.orFalse

internal data class ConfigurationSecurityUiState(
    val biometricIsEnabled: Boolean = false,
) {

    fun initUiState(biometricIsEnabled: Boolean?) =
        copy(biometricIsEnabled = biometricIsEnabled.orFalse)
}
