package com.composetest.core.data.datasources.local

import com.composetest.core.database.daos.ConfigurationEntityDao
import com.composetest.core.database.entities.configuration.ConfigurationEntity
import com.composetest.core.database.partialupdate.SecurityConfigurationUpdate
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

    suspend fun getLastConfiguration() = configurationEntityDao.getLastConfiguration()
}