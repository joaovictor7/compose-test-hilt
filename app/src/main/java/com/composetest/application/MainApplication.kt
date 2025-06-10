package com.composetest.application

import android.app.Application
import android.util.Log
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import com.composetest.common.provider.ApplicationModule
import com.composetest.core.analytic.event.ErrorAnalyticEvent
import com.composetest.core.analytic.sender.AnalyticSender
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
internal class MainApplication :
    Application(),
    Configuration.Provider,
    CoroutineScope by CoroutineScope(SupervisorJob() + Dispatchers.Default) {

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    @Inject
    lateinit var analyticSender: AnalyticSender

    @Inject
    lateinit var applicationModules: Array<ApplicationModule>

    override val workManagerConfiguration
        get() = Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()

    override fun onCreate() {
        super.onCreate()
        executeApplicationModules()
    }

    private fun executeApplicationModules() = applicationModules.forEach {
        runCatching {
            it.onCreate()
        }.onFailure {
            Log.e("Application Modules", it.message, it)
            launch { analyticSender.sendErrorEvent(ErrorAnalyticEvent(it)) }
        }
    }
}