package com.composetest.feature.login.data.repository

import com.composetest.core.data.workmanager.WorkManager
import com.composetest.core.domain.model.SessionModel
import com.composetest.core.domain.model.UserModel
import com.composetest.core.domain.repository.SessionRepository
import com.composetest.feature.login.data.datasource.local.SessionDataSource
import com.composetest.feature.login.data.mapper.SessionMapper
import com.composetest.feature.login.data.workes.MonitoringSessionWorker
import java.time.Duration
import javax.inject.Inject

internal class SessionRepositoryImpl @Inject constructor(
    private val sessionDataSource: SessionDataSource,
    private val workManager: WorkManager,
    private val sessionMapper: SessionMapper
) : SessionRepository {

    override suspend fun finishSession(id: Long) {
        sessionDataSource.finishSession(id)
    }

    override suspend fun getCurrentSession() =
        sessionMapper.mapperToModel(sessionDataSource.getCurrentSession())

    suspend fun insert(session: SessionModel, user: UserModel) {
        sessionDataSource.insert(sessionMapper.mapperToEntity(session, user))
    }

    fun enqueueMonitoringSessionWorker(workerDuration: Duration) {
        workManager.enqueueOneTimeWork(MonitoringSessionWorker.Builder(workerDuration))
    }
}