package com.composetest.feature.exchange.di

import androidx.lifecycle.SavedStateHandle
import com.composetest.core.analytic.AnalyticSender
import com.composetest.core.analytic.enums.ScreensAnalytic
import com.composetest.core.router.destinations.exchange.ExchangeDetailDestination
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
    fun provideExchangeDetailDestination(
        savedStateHandle: SavedStateHandle
    ): ExchangeDetailDestination = savedStateHandle.getDestination()

    @Provides
    @AsyncTaskUtilsQualifier(ScreensAnalytic.EXCHANGE_DETAIL)
    fun exchangeDetailAsyncTaskUtils(
        analyticSender: AnalyticSender
    ): AsyncTaskUtils = AsyncTaskUtils(analyticSender, ScreensAnalytic.EXCHANGE_DETAIL)

    @Provides
    @AsyncTaskUtilsQualifier(ScreensAnalytic.EXCHANGE_LIST)
    fun exchangeListAsyncTaskUtils(
        analyticSender: AnalyticSender
    ): AsyncTaskUtils = AsyncTaskUtils(analyticSender, ScreensAnalytic.EXCHANGE_LIST)
}