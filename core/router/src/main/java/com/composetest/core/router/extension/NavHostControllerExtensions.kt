package com.composetest.core.router.extension

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import com.composetest.core.router.enums.NavigationMode
import com.composetest.core.router.interfaces.ResultParam
import com.composetest.core.router.model.NavigationModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.transform
import kotlin.reflect.KClass

val NavHostController.currentRoute get() = currentDestination?.route?.substringBefore("?")

val NavHostController.currentRouteChangesFlow
    get() = currentBackStackEntryFlow
        .map { it.destination.route }
        .flowOn(Dispatchers.IO)

fun NavHostController.navigateTo(navigateModel: NavigationModel) {
    navigate(
        route = navigateModel.destination,
        navOptions = getNavigateOptions(navigateModel.navigationMode)
    )
}

fun NavHostController.navigateBack() {
    popBackStack()
}

fun <Result : ResultParam> NavHostController.navigateBack(result: Result) {
    previousBackStackEntry?.savedStateHandle?.set(
        result::class.simpleName.orEmpty(),
        result
    )
    navigateBack()
}

fun <Result : ResultParam> NavHostController.getResultFlow(resultClass: KClass<Result>) =
    currentBackStackEntryFlow.transform {
        with(it.savedStateHandle) {
            val key = resultClass.simpleName.orEmpty()
            get<Result>(key)?.let { result ->
                emit(result)
                remove<Result>(key)
            }
        }
    }

val NavHostController.backStackBundle get() = currentBackStackEntry?.arguments

private fun NavHostController.getNavigateOptions(mode: NavigationMode?) =
    NavOptions.Builder().apply {
        when (mode) {
            NavigationMode.REMOVE_CURRENT_SCREEN -> currentDestination?.id?.let {
                setPopUpTo(destinationId = it, inclusive = true)
            }
            NavigationMode.REMOVE_ALL_SCREENS_STACK -> {
                setPopUpTo(destinationId = 0, inclusive = true)
            }
            NavigationMode.SAVE_SCREEN_STATE -> runCatching {
                setPopUpTo(
                    destinationId = graph.findStartDestination().id,
                    inclusive = false,
                    saveState = true
                )
                setRestoreState(true)
            }
            else -> Unit
        }
        setLaunchSingleTop(true)
    }.build()