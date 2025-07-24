package com.composetest.core.analytic.impl.di

import com.composetest.core.analytic.api.sender.AnalyticSender
import com.composetest.core.analytic.impl.sender.AnalyticSenderImpl
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