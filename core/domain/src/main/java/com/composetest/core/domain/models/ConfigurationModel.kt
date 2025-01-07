package com.composetest.core.domain.models

data class ConfigurationModel(
    val id: Long = 0,
    val userId: String,
    val biometricLogin: Boolean = false,
)