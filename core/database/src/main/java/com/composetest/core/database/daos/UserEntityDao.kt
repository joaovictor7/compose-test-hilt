package com.composetest.core.database.daos

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.composetest.core.database.entities.UserEntity

@Dao
interface UserEntityDao {
    @Upsert
    suspend fun upsert(userEntity: UserEntity)

    @Query(
        "SELECT user.* FROM user " +
                "JOIN session ON user.userId = session.userId " +
                "WHERE session.isFinished == 0 " +
                "ORDER BY session.sessionId DESC " +
                "LIMIT 1"
    )
    suspend fun getCurrentUser(): UserEntity?

    @Query(
        "SELECT user.* FROM user " +
                "JOIN session ON session.userId = user.userId " +
                "WHERE session.endDate = (SELECT MAX(endDate) FROM session WHERE isFinished = 1)" +
                "LIMIT 1"
    )
    suspend fun getLastActiveUser(): UserEntity?
}
