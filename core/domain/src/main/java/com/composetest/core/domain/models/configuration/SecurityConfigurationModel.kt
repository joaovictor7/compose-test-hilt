package com.composetest.core.domain.models.configuration

data class SecurityConfigurationModel(
    val id: Long = 0,
    var biometricLogin: Boolean = false,
)
