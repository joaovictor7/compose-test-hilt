package com.composetest.core.data.workmanagers.workes

import android.content.Context
import android.content.res.Resources.NotFoundException
import androidx.hilt.work.HiltWorker
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkerParameters
import com.composetest.common.analytics.WorkerErrorAnalyticEvent
import com.composetest.core.data.data.repositories.local.SessionRepository
import com.composetest.core.data.data.repositories.remote.AnalyticsRepository
import com.composetest.core.data.enums.WorkManagerName
import com.composetest.core.data.workmanagers.WorkManager
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import java.time.Duration

@HiltWorker
class SessionWorker @AssistedInject constructor(
    private val sessionRepository: SessionRepository,
    private val analyticsRepository: AnalyticsRepository,
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork() = runCatching {
        val sessionId = sessionRepository.getCurrentSession()?.id
            ?: throw NotFoundException("Session not initialized")
        sessionRepository.finishSession(sessionId)
        Result.success()
    }.getOrElse {
        analyticsRepository.logNonFatalError(WorkerErrorAnalyticEvent(it))
        Result.failure()
    }

    class Builder(
        initialDelay: Duration
    ) : WorkManager.OneTimeWorkManager {
        override val work = WorkManagerName.SESSION
        override val existingWorkPolicy = ExistingWorkPolicy.REPLACE
        override val workRequest = OneTimeWorkRequestBuilder<SessionWorker>()
            .addTag(WorkManagerName.SESSION.name)
            .setConstraints(Constraints(requiredNetworkType = NetworkType.CONNECTED))
            .setInitialDelay(initialDelay)
            .build()
    }
}