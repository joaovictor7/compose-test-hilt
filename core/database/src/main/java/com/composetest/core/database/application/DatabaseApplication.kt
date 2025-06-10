package com.composetest.core.database.application

import com.composetest.common.provider.ApplicationModule

object DatabaseApplication : ApplicationModule {

    override fun onCreate() {
        System.loadLibrary("sqlcipher")
    }
}