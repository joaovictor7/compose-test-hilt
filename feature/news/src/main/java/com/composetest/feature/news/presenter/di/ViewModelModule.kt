package com.composetest.feature.news.presenter.di

import androidx.lifecycle.SavedStateHandle
import com.composetest.core.analytic.api.sender.AnalyticSender
import com.composetest.core.router.extension.getNavKey
import com.composetest.core.ui.di.qualifier.AsyncTaskUtilsQualifier
import com.composetest.core.ui.util.AsyncTaskUtils
import com.composetest.feature.news.analytic.screen.FullNewsScreenAnalytic
import com.composetest.feature.news.analytic.screen.NewsListScreenAnalytic
import com.composetest.feature.news.navigation.navkey.FullNewsNavKey
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal object ViewModelModule {

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

    @Provides
    fun fullNewsNavKey(
        savedStateHandle: SavedStateHandle
    ): FullNewsNavKey = savedStateHandle.getNavKey()
}