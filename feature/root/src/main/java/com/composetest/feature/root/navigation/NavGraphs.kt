package com.composetest.feature.root.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.composetest.core.router.interfaces.NavGraph
import com.composetest.feature.configuration.navigation.rootConfigurationNavGraphs
import com.composetest.feature.home.navigation.rootHomeNavGraph
import kotlin.reflect.KProperty

internal class NavGraphs(private val mainNavController: NavHostController) : NavGraph {
    override operator fun getValue(
        thisRef: Any?,
        property: KProperty<*>
    ): List<NavGraphBuilder.() -> Unit> =
        listOf(
            { rootHomeNavGraph() },
            { rootConfigurationNavGraphs(mainNavController) }
        )
}