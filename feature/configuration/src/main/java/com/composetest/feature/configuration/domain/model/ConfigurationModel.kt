package com.composetest.feature.configuration.domain.model

internal data class ConfigurationModel(
    val id: Long = 0,
    val userId: String,
    val security: SecurityConfigurationModel = SecurityConfigurationModel(),
)