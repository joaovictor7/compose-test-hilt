package com.composetest.core.data.androidapi.workmanager

interface WorkManager {
    fun enqueuePeriodicWork(workManagerRequest: WorkManagerRequest.PeriodicWorkManagerRequest)

    fun enqueueOneTimeWork(workManagerRequest: WorkManagerRequest.OneTimeWorkManagerRequest)
}