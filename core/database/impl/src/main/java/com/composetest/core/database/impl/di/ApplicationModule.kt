package com.composetest.core.database.impl.di

import com.composetest.common.api.application.ApplicationRunner
import com.composetest.common.api.di.qualifier.ApplicationRunnerQualifier
import com.composetest.common.api.enums.ApplicationModule
import com.composetest.core.database.impl.application.ApplicationRunnerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class ApplicationModule {

    @Binds
    @ApplicationRunnerQualifier(ApplicationModule.DATABASE)
    abstract fun applicationRunner(
        applicationRunnerImpl: ApplicationRunnerImpl
    ): ApplicationRunner
}