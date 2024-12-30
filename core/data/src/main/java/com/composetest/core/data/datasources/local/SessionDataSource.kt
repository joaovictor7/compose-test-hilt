package com.composetest.core.data.datasources.local

import com.composetest.core.database.daos.SessionEntityDao
import com.composetest.core.database.entities.SessionEntity
import com.composetest.core.database.entities.partialupdate.FinishedSessionEntityUpdate
import javax.inject.Inject

internal class SessionDataSource @Inject constructor(
    private val sessionEntityDao: SessionEntityDao
) {

    suspend fun insert(entity: SessionEntity) {
        sessionEntityDao.insert(entity)
    }

    suspend fun update(entity: FinishedSessionEntityUpdate) {
        sessionEntityDao.update(entity)
    }

    suspend fun getCurrentSession() = sessionEntityDao.getCurrentSession()
}