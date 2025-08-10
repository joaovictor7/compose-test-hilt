package com.composetest.data.di

import com.composetest.common.api.application.ApplicationRunner
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object ApplicationModule {

    @Provides
    fun applicationModules(
        databaseApplicationRunner: Set<@JvmSuppressWildcards ApplicationRunner>,
    ): Array<ApplicationRunner> = databaseApplicationRunner.toTypedArray()
}