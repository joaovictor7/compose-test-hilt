package com.composetest.feature.news.presenter.di

import androidx.lifecycle.SavedStateHandle
import com.composetest.core.analytic.AnalyticSender
import com.composetest.core.router.destinations.news.FullNewsDestination
import com.composetest.core.router.extensions.getDestination
import com.composetest.core.ui.di.qualifiers.AsyncTaskUtilsQualifier
import com.composetest.core.ui.utils.AsyncTaskUtils
import com.composetest.feature.news.analytic.screens.FullNewsScreenAnalytic
import com.composetest.feature.news.analytic.screens.NewsListScreenAnalytic
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
    @AsyncTaskUtilsQualifier(NewsListScreenAnalytic.SCREEN)
    fun newsListAsyncTaskUtils(
        analyticSender: AnalyticSender,
    ): AsyncTaskUtils = AsyncTaskUtils(analyticSender, NewsListScreenAnalytic)

    @Provides
    @AsyncTaskUtilsQualifier(FullNewsScreenAnalytic.SCREEN)
    fun fullNewsAsyncTaskUtils(
        analyticSender: AnalyticSender,
    ): AsyncTaskUtils = AsyncTaskUtils(analyticSender, FullNewsScreenAnalytic)
}