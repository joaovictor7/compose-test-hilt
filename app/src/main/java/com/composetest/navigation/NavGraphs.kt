package com.composetest.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.composetest.core.router.interfaces.NavGraph
import com.composetest.feature.configuration.presenter.navigation.configurationNavGraphs
import com.composetest.feature.exchange.presenter.navigation.exchangeNavGraphs
import com.composetest.feature.login.navigation.loginNavGraphs
import com.composetest.feature.news.navigation.newsNavGraphs
import com.composetest.feature.profile.navigation.profileNavGraphs
import com.composetest.feature.root.navigation.rootNavGraphs
import com.composetest.feature.weatherforecast.presenter.navigation.weatherForecastNavGraphs
import kotlin.reflect.KProperty

internal class NavGraphs(private val navController: NavHostController) : NavGraph {
    override operator fun getValue(
        thisRef: Any?,
        property: KProperty<*>
    ): List<NavGraphBuilder.() -> Unit> = listOf(
        { loginNavGraphs(navController) },
        { rootNavGraphs(navController) },
        { newsNavGraphs(navController) },
        { exchangeNavGraphs(navController) },
        { profileNavGraphs(navController) },
        { configurationNavGraphs() },
        { weatherForecastNavGraphs() },
    )
}