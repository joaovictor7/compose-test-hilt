package com.composetest.core.data.workmanager

import com.composetest.core.data.repository.WorkManagerRepository
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