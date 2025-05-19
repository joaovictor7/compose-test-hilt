package com.composetest.feature.weatherforecast.presenter.di

import com.composetest.core.analytic.AnalyticSender
import com.composetest.core.ui.di.qualifiers.AsyncTaskUtilsQualifier
import com.composetest.core.ui.utils.AsyncTaskUtils
import com.composetest.feature.weatherforecast.analytic.screens.WeatherForecastScreenAnalytic
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal object ViewModelModule {

    @Provides
    @AsyncTaskUtilsQualifier(WeatherForecastScreenAnalytic.SCREEN)
    fun weatherForecastAsyncTaskUtils(
        analyticSender: AnalyticSender
    ): AsyncTaskUtils = AsyncTaskUtils(analyticSender, WeatherForecastScreenAnalytic)
}