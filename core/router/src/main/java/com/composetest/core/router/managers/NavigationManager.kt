package com.composetest.core.router.managers

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavBackStackEntry
import com.composetest.core.router.enums.NavigationMode
import com.composetest.core.router.interfaces.ResultParam
import kotlinx.coroutines.flow.Flow

interface NavigationManager {
    val savedStateHandle: SavedStateHandle
    val navBackStackEntryFlow: Flow<NavBackStackEntry>

    fun <Destination : Any> navigate(
        destination: Destination,
        navigationMode: NavigationMode? = null
    )

    fun navigateBack()
    fun <Result : ResultParam> navigateBack(result: Result)
    suspend fun <Destination : Any> asyncNavigate(
        destination: Destination,
        navigationMode: NavigationMode? = null
    )

    suspend fun asyncNavigateBack()
    suspend fun <Result : ResultParam> asyncNavigateBack(result: Result)
    fun isCurrentScreen(destination: Any): Boolean
}