package com.composetest.core.data.impl.workmanager

import com.composetest.core.data.androidapi.workmanager.WorkManager
import com.composetest.core.data.androidapi.workmanager.WorkManagerRequest
import com.composetest.core.data.impl.repository.WorkManagerRepository
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