package com.composetest.feature.news.presenter.di

import androidx.lifecycle.SavedStateHandle
import com.composetest.core.analytic.sender.AnalyticSender
import com.composetest.core.router.extension.getDestination
import com.composetest.core.ui.di.qualifier.AsyncTaskUtilsQualifier
import com.composetest.core.ui.util.AsyncTaskUtils
import com.composetest.feature.news.analytic.screen.FullNewsScreenAnalytic
import com.composetest.feature.news.analytic.screen.NewsListScreenAnalytic
import com.composetest.feature.news.navigation.destination.FullNewsDestination
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
    @AsyncTaskUtilsQualifier(FullNewsScreenAnalytic.SCREEN)
    fun fullNewsAsyncTaskUtils(
        analyticSender: AnalyticSender
    ): AsyncTaskUtils = AsyncTaskUtils(analyticSender, FullNewsScreenAnalytic)

    @Provides
    @AsyncTaskUtilsQualifier(NewsListScreenAnalytic.SCREEN)
    fun newsListAsyncTaskUtils(
        analyticSender: AnalyticSender
    ): AsyncTaskUtils = AsyncTaskUtils(analyticSender, NewsListScreenAnalytic)
}