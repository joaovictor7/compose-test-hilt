package com.composetest.feature.home.di

import com.composetest.core.analytic.AnalyticSender
import com.composetest.core.analytic.events.home.HomeScreenAnalytic
import com.composetest.core.ui.di.qualifiers.AsyncTaskUtilsQualifier
import com.composetest.core.ui.utils.AsyncTaskUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal object ViewModelModule {

    @Provides
    @AsyncTaskUtilsQualifier(HomeScreenAnalytic.SCREEN)
    fun homeAsyncTaskUtils(
        analyticSender: AnalyticSender,
    ): AsyncTaskUtils = AsyncTaskUtils(analyticSender, HomeScreenAnalytic)
}