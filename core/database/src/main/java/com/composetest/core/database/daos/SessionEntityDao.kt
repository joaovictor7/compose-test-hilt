package com.composetest.core.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.composetest.core.database.entities.SessionEntity
import com.composetest.core.database.entities.partialupdate.EndDateSessionEntityUpdate

@Dao
interface SessionEntityDao {
    @Insert
    suspend fun insert(sessionEntity: SessionEntity)

    @Update(entity = SessionEntity::class)
    suspend fun update(endDateSessionEntityUpdate: EndDateSessionEntityUpdate)

    @Query(
        "SELECT * FROM session " +
            "WHERE endDate IS NULL " +
            "ORDER BY sessionId DESC " +
            "LIMIT 1"
    )
    suspend fun getCurrentSession(): SessionEntity?
}
