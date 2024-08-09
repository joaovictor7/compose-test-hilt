package com.composetest.core.data.providers

import com.composetest.core.data.workmanagers.WorkManager

interface WorkManagerProvider {

    fun createPeriodicWork(workManager: WorkManager.PeriodicWorkManager)

    fun createOneTimeWork(workManager: WorkManager.OneTimeWorkManager)
}