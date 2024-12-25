package com.composetest.core.data.managers

import com.composetest.core.data.repositories.WorkManagerRepository
import com.composetest.core.data.workmanager.WorkManagerRequest
import javax.inject.Inject

internal class WorkManagerImpl @Inject constructor(
    private val workManagerRepository: WorkManagerRepository
) : WorkManager {
    override fun enqueuePeriodicWork(workManagerRequest: WorkManagerRequest.PeriodicWorkManagerRequest) {
        workManagerRepository.enqueuePeriodicWork(workManagerRequest)
    }

    override fun enqueueOneTimeWork(workManagerRequest: WorkManagerRequest.OneTimeWorkManagerRequest) {
        workManagerRepository.enqueueOneTimeWork(workManagerRequest)
    }
}