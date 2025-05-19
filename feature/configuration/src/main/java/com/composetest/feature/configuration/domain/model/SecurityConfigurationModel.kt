package com.composetest.feature.configuration.domain.model

internal data class SecurityConfigurationModel(
    val id: Long = 0,
    var biometricLogin: Boolean = false,
)
