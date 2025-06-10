package com.composetest.data.di

import com.composetest.common.provider.ApplicationModule
import com.composetest.core.database.application.DatabaseApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object ApplicationModule {

    @Provides
    fun applicationModules(): Array<ApplicationModule> = arrayOf(
        DatabaseApplication
    )
}