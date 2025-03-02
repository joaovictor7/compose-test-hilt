package com.composetest.core.domain.repositories

import com.composetest.core.domain.models.configuration.SecurityConfigurationModel
import com.composetest.core.domain.models.configuration.ThemeConfigurationModel
import kotlinx.coroutines.flow.Flow

interface ConfigurationRepository {
    suspend fun getSecurityConfiguration(): SecurityConfigurationModel?
    suspend fun getLastSecurityConfiguration(): SecurityConfigurationModel?
    fun getThemeConfiguration(): Flow<ThemeConfigurationModel>
    suspend fun updateSecurityConfiguration(securityConfigurationModel: SecurityConfigurationModel)
    suspend fun updateThemeConfiguration(themeConfigurationModel: ThemeConfigurationModel)
}