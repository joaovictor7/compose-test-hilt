package com.composetest.navigation.di

import com.composetest.core.designsystem.navigation.DesignSystemNavGraph
import com.composetest.core.router.interfaces.NavGraph
import com.composetest.feature.account.navigation.AccountNavGraph
import com.composetest.feature.configuration.navigation.ConfigurationNavGraph
import com.composetest.feature.exchange.navigation.ExchangeNavGraph
import com.composetest.feature.login.navigation.LoginNavGraph
import com.composetest.feature.news.navigation.NewsNavGraph
import com.composetest.feature.root.navigation.RootNavGraph
import com.composetest.feature.weatherforecast.navigation.WeatherForecastNavGraph
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal class NavigationModule {

    @Provides
    fun teste(): Array<NavGraph> = arrayOf(
        DesignSystemNavGraph,
        LoginNavGraph,
        RootNavGraph,
        NewsNavGraph,
        ExchangeNavGraph,
        AccountNavGraph,
        WeatherForecastNavGraph,
        ConfigurationNavGraph,
    )
}