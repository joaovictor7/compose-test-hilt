package com.composetest.core.database.application

import com.composetest.common.application.ApplicationRunner
import javax.inject.Inject

internal class ApplicationRunnerImpl @Inject constructor() : ApplicationRunner {

    override fun onCreate() {
        System.loadLibrary("sqlcipher")
    }
}