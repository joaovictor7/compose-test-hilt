package com.composetest.feature.configuration.presenter.ui.security

import com.composetest.core.domain.extension.orFalse

internal data class SecurityConfigurationUiState(
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
