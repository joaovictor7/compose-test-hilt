package com.composetest.di

import com.composetest.analytics.MainAnalytic
import com.composetest.core.analytic.AnalyticSender
import com.composetest.core.ui.utils.AsyncTaskUtils
import com.composetest.di.qualifiers.MainAsyncTaskUtilsQualifier
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal object ViewModelModule {

    @Provides
    @MainAsyncTaskUtilsQualifier
    fun mainAsyncTaskUtils(
        analyticSender: AnalyticSender,
    ): AsyncTaskUtils = AsyncTaskUtils(analyticSender, MainAnalytic)
}