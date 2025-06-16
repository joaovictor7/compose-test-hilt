package com.composetest.feature.login.data.workes

import android.content.Context
import android.content.res.Resources.NotFoundException
import androidx.hilt.work.HiltWorker
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkerParameters
import com.composetest.core.analytic.event.ErrorAnalyticEvent
import com.composetest.core.analytic.sender.AnalyticSender
import com.composetest.core.data.enums.Worker
import com.composetest.core.data.workmanager.WorkManagerRequest
import com.composetest.core.domain.usecase.session.CheckSessionIsValidUseCase
import com.composetest.core.domain.usecase.session.FinishSessionUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import java.time.Duration

@HiltWorker
internal class MonitoringSessionWorker @AssistedInject constructor(
    private val checkSessionIsValidUseCase: CheckSessionIsValidUseCase,
    private val finishSessionUseCase: FinishSessionUseCase,
    private val analyticSender: AnalyticSender,
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork() = runCatching {
        val sessionValid = checkSessionIsValidUseCase()
        if (!sessionValid) throw NotFoundException("Session not initialized")
        finishSessionUseCase()
        Result.success()
    }.getOrElse {
        analyticSender.sendErrorEvent(ErrorAnalyticEvent(it))
        Result.failure()
    }

    class Builder(initialDelay: Duration) : WorkManagerRequest.OneTimeWorkManagerRequest {
        override val worker = Worker.SESSION
        override val existingWorkPolicy = ExistingWorkPolicy.REPLACE
        override val workRequest = OneTimeWorkRequestBuilder<MonitoringSessionWorker>()
            .addTag(Worker.SESSION.name)
            .setConstraints(Constraints(requiredNetworkType = NetworkType.CONNECTED))
            .setInitialDelay(initialDelay)
            .build()
    }
}