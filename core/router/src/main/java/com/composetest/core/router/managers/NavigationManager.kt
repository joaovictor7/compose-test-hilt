package com.composetest.core.router.managers

import androidx.lifecycle.SavedStateHandle
import com.composetest.core.router.enums.NavigationMode
import com.composetest.core.router.interfaces.ResultParam
import kotlinx.coroutines.flow.Flow
import kotlin.reflect.KClass

interface NavigationManager {
    val savedStateHandle: SavedStateHandle
    val currentRoute: String?
    val currentRouteChangesFlow: Flow<String>

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
    fun <Result : ResultParam> getResultFlow(resultClass: KClass<Result>): Flow<Result>
}