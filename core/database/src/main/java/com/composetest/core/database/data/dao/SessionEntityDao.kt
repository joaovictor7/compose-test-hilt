package com.composetest.core.database.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.composetest.core.database.data.entity.SessionEntity

@Dao
interface SessionEntityDao {
    @Insert
    suspend fun insert(sessionEntity: SessionEntity)

    @Query("UPDATE session SET isActive = 0 WHERE sessionId = :sessionId")
    suspend fun finishSession(sessionId: Long)

    @Query(
        "SELECT * FROM session " +
                "WHERE isActive = 1 " +
                "ORDER BY sessionId DESC " +
                "LIMIT 1"
    )
    suspend fun getCurrentSession(): SessionEntity?
}
