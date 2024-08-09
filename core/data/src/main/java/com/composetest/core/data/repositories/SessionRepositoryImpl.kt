package com.composetest.core.data.repositories

import com.composetest.core.data.datasources.local.SessionDataSource
import com.composetest.core.data.mappers.SessionMapper
import com.composetest.core.database.entities.partialupdate.FinishedSessionEntityUpdate
import com.composetest.core.domain.models.UserModel
import com.composetest.core.domain.models.session.SessionModel
import com.composetest.core.domain.repositories.SessionRepository
import javax.inject.Inject

internal class SessionRepositoryImpl @Inject constructor(
    private val sessionDataSource: SessionDataSource,
    private val sessionMapper: SessionMapper
) : SessionRepository {

    override suspend fun insert(session: SessionModel, user: UserModel) {
        sessionDataSource.insert(sessionMapper(session, user))
    }

    override suspend fun finishSession(sessionId: Long) {
        sessionDataSource.update(FinishedSessionEntityUpdate(sessionId, true))
    }

    override suspend fun getCurrentSession() =
        sessionMapper(sessionDataSource.getCurrentSession())
}