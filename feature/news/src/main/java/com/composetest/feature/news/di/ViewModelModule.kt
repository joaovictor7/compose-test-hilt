package com.composetest.feature.news.di

import androidx.lifecycle.SavedStateHandle
import com.composetest.core.analytic.AnalyticSender
import com.composetest.core.analytic.enums.ScreensAnalytic
import com.composetest.core.router.destinations.news.FullNewsDestination
import com.composetest.core.router.extensions.getDestination
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
    fun fullNewsDestination(
        savedStateHandle: SavedStateHandle
    ): FullNewsDestination = savedStateHandle.getDestination()

    @Provides
    @AsyncTaskUtilsQualifier(ScreensAnalytic.FULL_NEWS)
    fun fullNewsAsyncTaskUtils(
        analyticSender: AnalyticSender
    ): AsyncTaskUtils = AsyncTaskUtils(analyticSender, ScreensAnalytic.FULL_NEWS)

    @Provides
    @AsyncTaskUtilsQualifier(ScreensAnalytic.NEWS_LIST)
    fun newsListAsyncTaskUtils(
        analyticSender: AnalyticSender
    ): AsyncTaskUtils = AsyncTaskUtils(analyticSender, ScreensAnalytic.NEWS_LIST)
}