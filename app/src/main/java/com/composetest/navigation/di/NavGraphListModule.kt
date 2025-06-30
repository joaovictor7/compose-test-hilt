package com.composetest.navigation.di

import com.composetest.core.router.di.quailifier.NavGraphQualifier
import com.composetest.core.router.interfaces.NavGraph
import com.composetest.core.router.navgraph.MainNavGraph
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object NavGraphListModule {

    @Provides
    fun navGraphList(
        @NavGraphQualifier(MainNavGraph.NEWS_NAV_GRAPH) newsNavGraph: NavGraph,
        @NavGraphQualifier(MainNavGraph.DESIGN_SYSTEM_NAV_GRAPH) designSystemNavGraph: NavGraph,
        @NavGraphQualifier(MainNavGraph.LOGIN_NAV_GRAPH) loginNavGraph: NavGraph,
        @NavGraphQualifier(MainNavGraph.ROOT_NAV_GRAPH) rootNavGraph: NavGraph,
        @NavGraphQualifier(MainNavGraph.EXCHANGE_NAV_GRAPH) exchangeNavGraph: NavGraph,
        @NavGraphQualifier(MainNavGraph.ACCOUNT_NAV_GRAPH) accountNavGraph: NavGraph,
        @NavGraphQualifier(MainNavGraph.WEATHER_FORECAST_NAV_GRAPH) weatherForecastNavGraph: NavGraph,
        @NavGraphQualifier(MainNavGraph.CONFIGURATION_NAV_GRAPH) configurationNavGraph: NavGraph,
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