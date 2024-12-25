package com.composetest.core.data.repositories

import javax.inject.Inject
import androidx.work.WorkManager
import com.composetest.core.data.workmanager.WorkManagerRequest

internal class WorkManagerRepository @Inject constructor(
    private val workManager: WorkManager
) {
    fun enqueuePeriodicWork(workManagerRequest: WorkManagerRequest.PeriodicWorkManagerRequest) {
        workManager.enqueueUniquePeriodicWork(
            workManagerRequest.worker.name,
            workManagerRequest.existingPeriodicWorkPolicy,
            workManagerRequest.workRequest
        )
    }

    fun enqueueOneTimeWork(workManagerRequest: WorkManagerRequest.OneTimeWorkManagerRequest) {
        workManager.enqueueUniqueWork(
            workManagerRequest.worker.name,
            workManagerRequest.existingWorkPolicy,
            workManagerRequest.workRequest
        )
    }
}