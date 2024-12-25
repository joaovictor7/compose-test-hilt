package com.composetest.core.data.workmanager

import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.PeriodicWorkRequest
import com.composetest.core.data.enums.Worker

sealed interface WorkManagerRequest {
    val worker: Worker

    interface OneTimeWorkManagerRequest : WorkManagerRequest {
        val workRequest: OneTimeWorkRequest
        val existingWorkPolicy: ExistingWorkPolicy
    }

    interface PeriodicWorkManagerRequest : WorkManagerRequest {
        val workRequest: PeriodicWorkRequest
        val existingPeriodicWorkPolicy: ExistingPeriodicWorkPolicy
    }
}
