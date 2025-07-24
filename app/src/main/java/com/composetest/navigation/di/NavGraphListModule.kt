package com.composetest.navigation.di

import com.composetest.core.router.di.quailifier.NavGraphQualifier
import com.composetest.core.router.enums.ModuleNavGraph
import com.composetest.core.router.interfaces.NavGraph
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object NavGraphListModule {

    @Provides
    fun navGraphList(
        @NavGraphQualifier(ModuleNavGraph.NEWS_FEATURE) newsNavGraph: NavGraph,
        @NavGraphQualifier(ModuleNavGraph.DESIGN_SYSTEM_FEATURE) designSystemNavGraph: NavGraph,
        @NavGraphQualifier(ModuleNavGraph.LOGIN_FEATURE) loginNavGraph: NavGraph,
        @NavGraphQualifier(ModuleNavGraph.ROOT_FEATURE) rootNavGraph: NavGraph,
        @NavGraphQualifier(ModuleNavGraph.EXCHANGE_FEATURE) exchangeNavGraph: NavGraph,
        @NavGraphQualifier(ModuleNavGraph.ACCOUNT_FEATURE) accountNavGraph: NavGraph,
        @NavGraphQualifier(ModuleNavGraph.WEATHER_FORECAST_FEATURE) weatherForecastNavGraph: NavGraph,
        @NavGraphQualifier(ModuleNavGraph.CONFIGURATION_FEATURE) configurationNavGraph: NavGraph,
    ): Array<NavGraph> = arrayOf(
        designSystemNavGraph,
        loginNavGraph,
        rootNavGraph,
        newsNavGraph,
        exchangeNavGraph,
        accountNavGraph,
        weatherForecastNavGraph,
        configurationNavGraph,
    )
}