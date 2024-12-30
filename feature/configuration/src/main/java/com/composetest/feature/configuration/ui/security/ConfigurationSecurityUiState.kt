package com.composetest.feature.configuration.ui.security

import com.composetest.core.ui.interfaces.BaseUiState

internal data class ConfigurationSecurityUiState(
    val biometricIsEnabled: Boolean = false,
) : BaseUiState {

    fun initUiState(biometricIsEnabled: Boolean) = copy(biometricIsEnabled = biometricIsEnabled)
}
