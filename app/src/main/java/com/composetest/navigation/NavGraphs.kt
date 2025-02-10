package com.composetest.navigation

import com.composetest.feature.configuration.navigation.ConfigurationNavGraph
import com.composetest.feature.exchange.navigation.ExchangeNavGraph
import com.composetest.feature.login.navigation.LoginNavGraph
import com.composetest.feature.news.navigation.NewsNavGraph
import com.composetest.feature.profile.navigation.ProfileNavGraph
import com.composetest.feature.root.navigation.RootNavGraph
import com.composetest.feature.weatherforecast.navigation.WeatherForecastNavGraph

internal val navGraphs = listOf(
    ConfigurationNavGraph,
    LoginNavGraph,
    RootNavGraph,
    ProfileNavGraph,
    WeatherForecastNavGraph,
    NewsNavGraph,
    ExchangeNavGraph,
)