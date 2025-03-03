package com.composetest.core.domain.models.configuration

data class ConfigurationModel(
    val id: Long = 0,
    val userId: String,
    val security: SecurityConfigurationModel = SecurityConfigurationModel(),
)