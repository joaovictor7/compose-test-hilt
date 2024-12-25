package com.composetest.core.data.managers

import com.composetest.core.data.workmanager.WorkManagerRequest

interface WorkManager {
    fun enqueuePeriodicWork(workManagerRequest: WorkManagerRequest.PeriodicWorkManagerRequest)
    fun enqueueOneTimeWork(workManagerRequest: WorkManagerRequest.OneTimeWorkManagerRequest)
}