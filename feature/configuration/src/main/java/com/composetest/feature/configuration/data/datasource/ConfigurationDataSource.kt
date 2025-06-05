package com.composetest.feature.configuration.data.datasource

import com.composetest.core.database.data.dao.ConfigurationEntityDao
import com.composetest.core.database.data.dao.partialupdate.SecurityConfigurationUpdate
import com.composetest.core.database.data.entity.configuration.ConfigurationEntity
import javax.inject.Inject

internal class ConfigurationDataSource @Inject constructor(
    private val configurationEntityDao: ConfigurationEntityDao
) {

    suspend fun getConfiguration() = configurationEntityDao.getCurrentConfiguration()

    suspend fun upsert(configurationEntity: ConfigurationEntity) {
        configurationEntityDao.upsert(configurationEntity)
    }

    suspend fun updateSecurityConfiguration(securityConfigurationUpdate: SecurityConfigurationUpdate) {
        configurationEntityDao.update(securityConfigurationUpdate)
    }

    suspend fun getLastBiometricConfiguration() =
        configurationEntityDao.getLastBiometricConfiguration()
}