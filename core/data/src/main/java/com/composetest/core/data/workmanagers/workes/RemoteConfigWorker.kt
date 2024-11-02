package com.composetest.core.data.workmanagers.workes

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkerParameters
import com.composetest.core.data.enums.WorkManagerName
import com.composetest.core.data.workmanagers.WorkManager
import com.composetest.core.domain.models.analytics.ErrorAnalyticEvent
import com.composetest.core.domain.repositories.RemoteConfigRepository
import com.composetest.core.domain.usecases.SendAnalyticsUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlin.time.Duration.Companion.minutes
import kotlin.time.toJavaDuration

@HiltWorker
class RemoteConfigWorker @AssistedInject constructor(
    private val remoteConfigRepository: RemoteConfigRepository,
    private val sendAnalyticsUseCase: SendAnalyticsUseCase,
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork() = runCatching {
        remoteConfigRepository.fetch()
        Result.success()
    }.getOrElse {
        sendAnalyticsUseCase(ErrorAnalyticEvent(it))
        Result.failure()
    }

    object Builder : WorkManager.PeriodicWorkManager {
        override val work = WorkManagerName.REMOTE_CONFIG
        override val existingPeriodicWorkPolicy = ExistingPeriodicWorkPolicy.UPDATE
        override val workRequest =
            PeriodicWorkRequestBuilder<RemoteConfigWorker>(15.minutes.toJavaDuration())
                .addTag(WorkManagerName.REMOTE_CONFIG.name)
                .setConstraints(Constraints(requiredNetworkType = NetworkType.CONNECTED))
                .build()
    }
}