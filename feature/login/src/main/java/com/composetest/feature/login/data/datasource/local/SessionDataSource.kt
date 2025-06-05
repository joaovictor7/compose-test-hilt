package com.composetest.feature.login.data.datasource.local

import com.composetest.core.database.data.dao.SessionEntityDao
import com.composetest.core.database.data.entity.SessionEntity
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