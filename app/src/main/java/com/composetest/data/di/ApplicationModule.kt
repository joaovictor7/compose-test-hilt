package com.composetest.data.di

import com.composetest.common.application.ApplicationRunner
import com.composetest.common.application.di.qualifier.ApplicationRunnerQualifier
import com.composetest.common.application.enums.ApplicationModule
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object ApplicationModule {

    @Provides
    fun applicationModules(
        @ApplicationRunnerQualifier(ApplicationModule.DATABASE) databaseApplicationRunner: ApplicationRunner,
    ): Array<ApplicationRunner> = arrayOf(
        databaseApplicationRunner
    )
}