package com.composetest.core.database.di

import com.composetest.common.application.di.qualifier.ApplicationRunnerQualifier
import com.composetest.common.application.ApplicationRunner
import com.composetest.common.application.enums.ApplicationModule
import com.composetest.core.database.application.ApplicationRunnerImpl
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