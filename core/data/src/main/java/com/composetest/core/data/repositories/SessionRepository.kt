package com.composetest.core.data.repositories

import com.composetest.core.data.datasources.local.SessionDataSource
import com.composetest.core.data.mappers.SessionMapper
import com.composetest.core.database.entities.partialupdate.FinishedSessionEntityUpdate
import com.composetest.core.domain.models.UserModel
import com.composetest.core.domain.models.session.SessionModel
import javax.inject.Inject

internal class SessionRepository @Inject constructor(
    private val sessionDataSource: SessionDataSource,
    private val sessionMapper: SessionMapper
) {

    suspend fun insert(session: SessionModel, user: UserModel) {
        sessionDataSource.insert(sessionMapper(session, user))
    }

    suspend fun finishSession(sessionId: Long) {
        sessionDataSource.update(FinishedSessionEntityUpdate(sessionId, true))
    }

    suspend fun getCurrentSession() = sessionMapper(sessionDataSource.getCurrentSession())
}