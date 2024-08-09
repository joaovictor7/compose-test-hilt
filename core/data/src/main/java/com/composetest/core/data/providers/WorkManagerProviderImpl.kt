package com.composetest.core.data.providers

import javax.inject.Inject
import androidx.work.WorkManager as WorkManagerFramework
import com.composetest.core.data.workmanagers.WorkManager as WorkManagerInterface

internal class WorkManagerProviderImpl @Inject constructor(
    private val workManager: WorkManagerFramework
) : WorkManagerProvider {

    override fun createPeriodicWork(workManager: WorkManagerInterface.PeriodicWorkManager) {
        this.workManager.enqueueUniquePeriodicWork(
            workManager.work.name,
            workManager.existingPeriodicWorkPolicy,
            workManager.workRequest
        )
    }

    override fun createOneTimeWork(workManager: WorkManagerInterface.OneTimeWorkManager) {
        this.workManager.enqueueUniqueWork(
            workManager.work.name,
            workManager.existingWorkPolicy,
            workManager.workRequest
        )
    }
}