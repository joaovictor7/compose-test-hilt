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
        @NavGraphQualifier(ModuleNavGraph.NEWS_NAV_GRAPH) newsNavGraph: NavGraph,
        @NavGraphQualifier(ModuleNavGraph.DESIGN_SYSTEM_NAV_GRAPH) designSystemNavGraph: NavGraph,
        @NavGraphQualifier(ModuleNavGraph.LOGIN_NAV_GRAPH) loginNavGraph: NavGraph,
        @NavGraphQualifier(ModuleNavGraph.ROOT_NAV_GRAPH) rootNavGraph: NavGraph,
        @NavGraphQualifier(ModuleNavGraph.EXCHANGE_NAV_GRAPH) exchangeNavGraph: NavGraph,
        @NavGraphQualifier(ModuleNavGraph.ACCOUNT_NAV_GRAPH) accountNavGraph: NavGraph,
        @NavGraphQualifier(ModuleNavGraph.WEATHER_FORECAST_NAV_GRAPH) weatherForecastNavGraph: NavGraph,
        @NavGraphQualifier(ModuleNavGraph.CONFIGURATION_NAV_GRAPH) configurationNavGraph: NavGraph,
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