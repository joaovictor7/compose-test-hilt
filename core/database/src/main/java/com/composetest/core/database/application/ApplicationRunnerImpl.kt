package com.composetest.core.database.application

import com.composetest.common.application.ApplicationRunner
import javax.inject.Inject

internal class ApplicationRunnerImpl @Inject constructor() : ApplicationRunner {

    override fun onCreate() {
        System.loadLibrary(SQL_CIPHER_LIB_NAME)
    }

    private companion object {
        const val SQL_CIPHER_LIB_NAME = "sqlcipher"
    }
}