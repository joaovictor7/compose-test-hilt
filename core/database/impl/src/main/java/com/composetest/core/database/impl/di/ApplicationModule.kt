package com.composetest.core.database.impl.di

import com.composetest.common.api.application.ApplicationRunner
import com.composetest.core.database.impl.application.ApplicationRunnerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)
internal abstract class ApplicationModule {

    @Binds
    @IntoSet
    abstract fun applicationRunner(
        applicationRunnerImpl: ApplicationRunnerImpl
    ): ApplicationRunner
}