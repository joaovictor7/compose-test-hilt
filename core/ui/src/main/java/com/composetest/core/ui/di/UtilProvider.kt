package com.composetest.core.ui.di

import com.composetest.core.analytic.sender.AnalyticSender
import com.composetest.core.ui.util.AsyncTaskUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object UtilProvider {

    @Provides
    fun asyncTaskUtils(
        analyticSender: AnalyticSender
    ): AsyncTaskUtils = AsyncTaskUtils(analyticSender)
}