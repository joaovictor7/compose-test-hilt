package com.composetest.feature.configuration.ui.security

import com.composetest.common.extensions.orFalse

internal data class ConfigurationSecurityUiState(
    val biometricIsAvailable: Boolean = true,
    val biometricIsEnabled: Boolean = false,
) {

    fun initUiState(
        biometricIsAvailable: Boolean,
        biometricIsEnabled: Boolean?
    ) = copy(
        biometricIsAvailable = biometricIsAvailable,
        biometricIsEnabled = biometricIsEnabled.orFalse
    )
}
