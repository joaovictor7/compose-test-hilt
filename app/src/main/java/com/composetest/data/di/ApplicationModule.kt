package com.composetest.data.di

import com.composetest.common.api.application.ApplicationRunner
import com.composetest.common.api.di.qualifier.ApplicationRunnerQualifier
import com.composetest.common.api.enums.ApplicationModule
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