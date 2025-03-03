package com.composetest.core.data.workmanager.workes

import android.content.Context
import android.content.res.Resources.NotFoundException
import androidx.hilt.work.HiltWorker
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkerParameters
import com.composetest.core.analytic.AnalyticSender
import com.composetest.core.analytic.events.ErrorAnalyticEvent
import com.composetest.core.data.enums.Worker
import com.composetest.core.data.workmanager.WorkManagerRequest
import com.composetest.core.domain.managers.SessionManager
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import java.time.Duration

@HiltWorker
class SessionWorker @AssistedInject constructor(
    private val sessionManager: SessionManager,
    private val analyticSender: AnalyticSender,
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork() = runCatching {
        val sessionValid = sessionManager.sessionIsLogged()
        if (!sessionValid) throw NotFoundException("Session not initialized")
        sessionManager.finishCurrentSession()
        Result.success()
    }.getOrElse {
        analyticSender.sendErrorEvent(ErrorAnalyticEvent(it))
        Result.failure()
    }

    class Builder(initialDelay: Duration) : WorkManagerRequest.OneTimeWorkManagerRequest {
        override val worker = Worker.SESSION
        override val existingWorkPolicy = ExistingWorkPolicy.REPLACE
        override val workRequest = OneTimeWorkRequestBuilder<SessionWorker>()
            .addTag(Worker.SESSION.name)
            .setConstraints(Constraints(requiredNetworkType = NetworkType.CONNECTED))
            .setInitialDelay(initialDelay)
            .build()
    }
}