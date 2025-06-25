package com.composetest.feature.exchange.presenter.di

import androidx.lifecycle.SavedStateHandle
import com.composetest.core.analytic.sender.AnalyticSender
import com.composetest.core.router.extension.getDestination
import com.composetest.core.ui.di.qualifier.AsyncTaskUtilsQualifier
import com.composetest.core.ui.util.AsyncTaskUtils
import com.composetest.feature.exchange.analytic.screen.ExchangeDetailScreenAnalytic
import com.composetest.feature.exchange.analytic.screen.ExchangeListScreenAnalytic
import com.composetest.feature.exchange.navigation.destination.ExchangeDetailDestination
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
    @AsyncTaskUtilsQualifier(ExchangeDetailScreenAnalytic.SCREEN)
    fun exchangeDetailAsyncTaskUtils(
        analyticSender: AnalyticSender
    ): AsyncTaskUtils = AsyncTaskUtils(analyticSender, ExchangeDetailScreenAnalytic)

    @Provides
    @AsyncTaskUtilsQualifier(ExchangeListScreenAnalytic.SCREEN)
    fun exchangeListAsyncTaskUtils(
        analyticSender: AnalyticSender
    ): AsyncTaskUtils = AsyncTaskUtils(analyticSender, ExchangeListScreenAnalytic)
}