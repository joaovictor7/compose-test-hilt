package com.composetest.feature.configuration.domain.models

internal data class ConfigurationModel(
    val id: Long = 0,
    val userId: String,
    val security: SecurityConfigurationModel = SecurityConfigurationModel(),
)