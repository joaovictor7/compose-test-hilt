package com.composetest.application

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import com.composetest.common.provider.ApplicationModule
import com.composetest.core.ui.util.AsyncTaskUtils
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Inject

@HiltAndroidApp
internal class MainApplication :
    Application(),
    Configuration.Provider,
    CoroutineScope by CoroutineScope(SupervisorJob() + Dispatchers.Default) {

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    @Inject
    lateinit var asyncTaskUtils: AsyncTaskUtils

    @Inject
    lateinit var moduleApplications: Array<ApplicationModule>

    override val workManagerConfiguration
        get() = Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()

    override fun onCreate() {
        super.onCreate()
        executeModuleApplications()
    }

    private fun executeModuleApplications() = asyncTaskUtils.runAsyncTask(this) {
        moduleApplications.forEach { it.onCreate(this) }
    }
}