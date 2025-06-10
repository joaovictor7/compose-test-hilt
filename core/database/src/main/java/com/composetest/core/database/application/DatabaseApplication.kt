package com.composetest.core.database.application

import android.content.Context
import com.composetest.common.provider.ApplicationModule

object DatabaseApplication : ApplicationModule {

    override fun onCreate(context: Context) {
        System.loadLibrary("sqlcipher")
    }
}