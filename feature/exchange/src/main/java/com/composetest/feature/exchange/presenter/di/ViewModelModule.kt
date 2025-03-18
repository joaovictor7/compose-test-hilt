package com.composetest.feature.exchange.presenter.di

import androidx.lifecycle.SavedStateHandle
import com.composetest.core.analytic.AnalyticSender
import com.composetest.core.router.destinations.exchange.ExchangeDetailDestination
import com.composetest.core.router.extensions.getDestination
import com.composetest.core.ui.di.qualifiers.AsyncTaskUtilsQualifier
import com.composetest.core.ui.utils.AsyncTaskUtils
import com.composetest.feature.exchange.analytics.screens.ExchangeDetailScreenAnalytic
import com.composetest.feature.exchange.analytics.screens.ExchangeListScreenAnalytic
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal object ViewModelModule {

    @Provides
    fun provideExchangeDetailDestination(
        savedStateHandle: SavedStateHandle
    ): ExchangeDetailDestination = savedStateHandle.getDestination()

    @Provides
    @AsyncTaskUtilsQualifier(ExchangeListScreenAnalytic.SCREEN)
    fun exchangeListAsyncTaskUtils(
        analyticSender: AnalyticSender,
    ): AsyncTaskUtils = AsyncTaskUtils(analyticSender, ExchangeListScreenAnalytic)

    @Provides
    @AsyncTaskUtilsQualifier(ExchangeDetailScreenAnalytic.SCREEN)
    fun exchangeDetailAsyncTaskUtils(
        analyticSender: AnalyticSender,
    ): AsyncTaskUtils = AsyncTaskUtils(analyticSender, ExchangeListScreenAnalytic)
}