package com.composetest.core.data.data.repositories.local

import com.composetest.core.database.entities.SessionEntity

interface SessionRepository {
    suspend fun insert(entity: SessionEntity)
    suspend fun finishSession(sessionId: Long)
    suspend fun <T> getCurrentSession(mapper: (SessionEntity?) -> T): T
}