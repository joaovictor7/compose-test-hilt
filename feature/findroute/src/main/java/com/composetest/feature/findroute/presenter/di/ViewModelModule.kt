package com.composetest.feature.findroute.presenter.di

import androidx.lifecycle.SavedStateHandle
import com.composetest.core.analytic.api.sender.AnalyticSender
import com.composetest.core.router.destination.findroute.FindRouteDestination
import com.composetest.core.router.extension.getDestination
import com.composetest.core.ui.di.qualifier.AsyncTaskUtilsQualifier
import com.composetest.core.ui.util.AsyncTaskUtils
import com.composetest.feature.findroute.analytic.screen.FindRouteScreenAnalytic
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal object ViewModelModule {
    @Provides
    fun findRouteDestination(
        savedStateHandle: SavedStateHandle
    ): FindRouteDestination = savedStateHandle.getDestination()

    @Provides
    @AsyncTaskUtilsQualifier(FindRouteScreenAnalytic.SCREEN)
    fun findRouteAsyncTaskUtils(
        analyticSender: AnalyticSender
    ): AsyncTaskUtils = AsyncTaskUtils(analyticSender, FindRouteScreenAnalytic)
}