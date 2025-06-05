package com.composetest.feature.configuration.presenter.di

import com.composetest.core.analytic.sender.AnalyticSender
import com.composetest.core.ui.di.qualifier.AsyncTaskUtilsQualifier
import com.composetest.core.ui.util.AsyncTaskUtils
import com.composetest.feature.configuration.analytic.screen.ConfigurationScreenAnalytic
import com.composetest.feature.configuration.analytic.screen.SecurityConfigurationScreenAnalytic
import com.composetest.feature.configuration.analytic.screen.ThemeConfigurationScreenAnalytic
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal object ViewModelModule {

    @Provides
    @AsyncTaskUtilsQualifier(ConfigurationScreenAnalytic.SCREEN)
    fun configurationAsyncTaskUtils(
        analyticSender: AnalyticSender
    ): AsyncTaskUtils = AsyncTaskUtils(analyticSender, ConfigurationScreenAnalytic)

    @Provides
    @AsyncTaskUtilsQualifier(ThemeConfigurationScreenAnalytic.SCREEN)
    fun themeAsyncTaskUtils(
        analyticSender: AnalyticSender
    ): AsyncTaskUtils = AsyncTaskUtils(analyticSender, ThemeConfigurationScreenAnalytic)

    @Provides
    @AsyncTaskUtilsQualifier(SecurityConfigurationScreenAnalytic.SCREEN)
    fun securityAsyncTaskUtils(
        analyticSender: AnalyticSender
    ): AsyncTaskUtils = AsyncTaskUtils(analyticSender, SecurityConfigurationScreenAnalytic)
}