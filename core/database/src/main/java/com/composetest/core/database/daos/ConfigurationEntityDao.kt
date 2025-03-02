package com.composetest.core.database.daos

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.composetest.core.database.entities.configuration.ConfigurationEntity
import com.composetest.core.database.partialupdate.SecurityConfigurationUpdate

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
        "SELECT configuration.* FROM configuration " +
                "JOIN user ON user.userId = configuration.userId " +
                "JOIN session ON session.userId = user.userId " +
                "WHERE session.endDate = (SELECT MAX(endDate) FROM session)" +
                "LIMIT 1"
    )
    suspend fun getLastConfiguration(): ConfigurationEntity?
}
