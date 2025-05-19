package com.composetest.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.composetest.core.router.interfaces.NavGraph
import com.composetest.feature.configuration.navigation.configurationNavGraphs
import com.composetest.feature.exchange.navigation.exchangeNavGraphs
import com.composetest.feature.login.navigation.loginNavGraphs
import com.composetest.feature.news.navigation.newsNavGraphs
import com.composetest.feature.account.navigation.accountNavGraphs
import com.composetest.feature.root.navigation.rootNavGraphs
import com.composetest.feature.weatherforecast.navigation.weatherForecastNavGraphs
import kotlin.reflect.KProperty

internal class ScreenNavGraphs(private val navController: NavHostController) : NavGraph {
    override operator fun getValue(
        thisRef: Any?,
        property: KProperty<*>
    ): List<NavGraphBuilder.() -> Unit> = listOf(
        { loginNavGraphs(navController) },
        { rootNavGraphs(navController) },
        { newsNavGraphs(navController) },
        { exchangeNavGraphs(navController) },
        { accountNavGraphs(navController) },
        { weatherForecastNavGraphs(navController) },
        { configurationNavGraphs() },
    )
}