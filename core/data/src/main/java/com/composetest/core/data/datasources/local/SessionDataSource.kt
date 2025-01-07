package com.composetest.core.data.datasources.local

import com.composetest.core.database.daos.SessionEntityDao
import com.composetest.core.database.entities.SessionEntity
import javax.inject.Inject

internal class SessionDataSource @Inject constructor(
    private val sessionEntityDao: SessionEntityDao
) {

    suspend fun insert(entity: SessionEntity) {
        sessionEntityDao.insert(entity)
    }

    suspend fun finishSession(sessionId: Long) {
        sessionEntityDao.finishSession(sessionId)
    }

    suspend fun getCurrentSession() = sessionEntityDao.getCurrentSession()
}