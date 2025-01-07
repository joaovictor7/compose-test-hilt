package com.composetest.core.data.datasources.local

import com.composetest.core.database.daos.ConfigurationEntityDao
import com.composetest.core.database.entities.ConfigurationEntity
import javax.inject.Inject

internal class ConfigurationDataSource @Inject constructor(
    private val configurationEntityDao: ConfigurationEntityDao
) {

    suspend fun getConfiguration() = configurationEntityDao.getCurrentConfiguration()

    suspend fun upsert(configurationEntity: ConfigurationEntity) {
        configurationEntityDao.upsert(configurationEntity)
    }

    suspend fun getLastConfiguration() = configurationEntityDao.getLastConfiguration()
}