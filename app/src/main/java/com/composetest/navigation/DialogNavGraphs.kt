package com.composetest.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.dialog
import com.composetest.core.router.destination.dialog.GenericErrorDialog
import com.composetest.core.router.destination.dialog.NetworkErrorDialog
import com.composetest.core.router.interfaces.NavGraph
import com.composetest.presentation.ui.dialog.GenericErrorDialog
import com.composetest.presentation.ui.dialog.NetworkErrorDialog
import kotlin.reflect.KProperty

internal class DialogNavGraphs(private val navController: NavHostController) : NavGraph {
    override operator fun getValue(
        thisRef: Any?,
        property: KProperty<*>
    ): List<NavGraphBuilder.() -> Unit> = listOf(
        { dialog<GenericErrorDialog> { GenericErrorDialog(navController) } },
        { dialog<NetworkErrorDialog> { NetworkErrorDialog(navController) } },
    )
}