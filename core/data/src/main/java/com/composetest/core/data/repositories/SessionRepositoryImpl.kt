package com.composetest.core.data.repositories

import com.composetest.core.data.datasources.local.SessionDataSource
import com.composetest.core.data.mappers.SessionMapper
import com.composetest.core.data.workmanager.WorkManager
import com.composetest.core.data.workmanager.workes.SessionWorker
import com.composetest.core.domain.models.UserModel
import com.composetest.core.domain.models.session.SessionModel
import com.composetest.core.domain.repositories.SessionRepository
import java.time.Duration
import javax.inject.Inject

internal class SessionRepositoryImpl @Inject constructor(
    private val sessionDataSource: SessionDataSource,
    private val workManager: WorkManager,
    private val sessionMapper: SessionMapper
) : SessionRepository {

    override suspend fun insert(session: SessionModel, user: UserModel) {
        sessionDataSource.insert(sessionMapper.mapperToEntity(session, user))
        sessionScheduling(session)
    }

    override suspend fun finishSession(id: Long) {
        sessionDataSource.finishSession(id)
    }

    override suspend fun getCurrentSession() =
        sessionMapper.mapperToModel(sessionDataSource.getCurrentSession())

    private fun sessionScheduling(session: SessionModel) {
        val duration = Duration.between(session.startDate, session.endDate)
        workManager.enqueueOneTimeWork(SessionWorker.Builder(duration))
    }
}