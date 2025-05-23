package com.composetest.core.data.di

import android.content.Context
import com.composetest.core.data.workmanager.WorkManagerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import androidx.work.WorkManager as AndroidWorkManager
import com.composetest.core.data.workmanager.WorkManager as InterfaceWorkManager

@Module
@InstallIn(SingletonComponent::class)
internal object WorkManagerModule {

    @Provides
    fun androidWorkManager(
        @ApplicationContext context: Context
    ): AndroidWorkManager = AndroidWorkManager.getInstance(context)


    @Provides
    fun interfaceWorkManager(
        workManager: WorkManagerImpl
    ): InterfaceWorkManager = workManager
}