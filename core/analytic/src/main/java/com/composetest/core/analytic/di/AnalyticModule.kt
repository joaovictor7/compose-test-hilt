package com.composetest.core.analytic.di

import com.composetest.core.analytic.sender.AnalyticSender
import com.composetest.core.analytic.sender.AnalyticSenderImpl
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