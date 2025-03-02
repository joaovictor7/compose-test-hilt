package com.composetest.core.domain.models

import com.composetest.core.domain.models.configuration.SecurityConfigurationModel

data class ConfigurationModel(
    val id: Long = 0,
    val userId: String,
    val security: SecurityConfigurationModel = SecurityConfigurationModel(),
)