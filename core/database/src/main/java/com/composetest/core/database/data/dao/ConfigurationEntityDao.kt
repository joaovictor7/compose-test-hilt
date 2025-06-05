package com.composetest.core.database.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.composetest.core.database.data.dao.partialupdate.SecurityConfigurationUpdate
import com.composetest.core.database.data.entity.configuration.ConfigurationEntity

@Dao
interface ConfigurationEntityDao {
    @Upsert
    suspend fun upsert(configurationEntity: ConfigurationEntity)

    @Update(entity = ConfigurationEntity::class)
    suspend fun update(securityConfigurationUpdate: SecurityConfigurationUpdate)

    @Query(
        "SELECT configuration.* FROM configuration " +
                "JOIN user ON user.userId = configuration.userId " +
                "JOIN session ON session.userId = user.userId " +
                "WHERE session.isActive == 1 " +
                "LIMIT 1"
    )
    suspend fun getCurrentConfiguration(): ConfigurationEntity?

    @Query(
        "SELECT configuration.biometricLogin FROM configuration " +
                "JOIN user ON user.userId = configuration.userId " +
                "JOIN session ON session.userId = user.userId " +
                "WHERE session.endDate = (SELECT MAX(endDate) FROM session)" +
                "LIMIT 1"
    )
    suspend fun getLastBiometricConfiguration(): Boolean?
}
