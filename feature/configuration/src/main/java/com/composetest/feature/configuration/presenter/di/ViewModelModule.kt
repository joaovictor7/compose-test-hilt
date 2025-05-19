package com.composetest.feature.configuration.presenter.di

import com.composetest.core.analytic.AnalyticSender
import com.composetest.core.ui.di.qualifiers.AsyncTaskUtilsQualifier
import com.composetest.core.ui.utils.AsyncTaskUtils
import com.composetest.feature.configuration.analytic.screens.ConfigurationScreenAnalytic
import com.composetest.feature.configuration.analytic.screens.SecurityConfigurationScreenAnalytic
import com.composetest.feature.configuration.analytic.screens.ThemeConfigurationScreenAnalytic
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