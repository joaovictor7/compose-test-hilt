package com.composetest.core.data.managers

import com.composetest.core.data.repositories.WorkManagerRepository
import com.composetest.core.data.workmanager.WorkManagerRequest
import javax.inject.Inject

internal class WorkManager @Inject constructor(
    private val workManagerRepository: WorkManagerRepository
) {
    fun enqueuePeriodicWork(workManagerRequest: WorkManagerRequest.PeriodicWorkManagerRequest) {
        workManagerRepository.enqueuePeriodicWork(workManagerRequest)
    }

    fun enqueueOneTimeWork(workManagerRequest: WorkManagerRequest.OneTimeWorkManagerRequest) {
        workManagerRepository.enqueueOneTimeWork(workManagerRequest)
    }
}