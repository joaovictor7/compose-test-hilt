package com.composetest.core.domain.models.configuration

internal data class ConfigurationModel(
    val id: Long = 0,
    val userId: String,
    val security: SecurityConfigurationModel = SecurityConfigurationModel(),
)