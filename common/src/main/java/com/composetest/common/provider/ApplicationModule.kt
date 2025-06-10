package com.composetest.common.provider

import android.content.Context

interface ApplicationModule {
    fun onCreate(context: Context)
}