package com.composetest.core.database.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.composetest.core.database.data.entity.UserEntity

@Dao
interface UserEntityDao {
    @Upsert
    suspend fun upsert(userEntity: UserEntity)

    @Query(
        "SELECT user.* FROM user " +
                "JOIN session ON user.userId = session.userId " +
                "WHERE session.isActive == 1 " +
                "ORDER BY session.sessionId DESC " +
                "LIMIT 1"
    )
    suspend fun getCurrentUser(): UserEntity?

    @Query(
        "SELECT user.* FROM user " +
                "JOIN session ON session.userId = user.userId " +
                "WHERE session.endDate = (SELECT MAX(endDate) FROM session WHERE isActive = 0)" +
                "LIMIT 1"
    )
    suspend fun getLastActiveUser(): UserEntity?
}
