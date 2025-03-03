package com.composetest.core.data.di

import com.composetest.core.analytic.AnalyticSender
import com.composetest.core.data.analytic.AnalyticSenderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class AnalyticModule {

    @Binds
    abstract fun analyticSender(
        analyticSenderImpl: AnalyticSenderImpl
    ): AnalyticSender
}