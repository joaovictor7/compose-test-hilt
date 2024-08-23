package com.composetest.core.router.managers

import androidx.lifecycle.SavedStateHandle
import com.composetest.core.router.enums.NavigationMode
import com.composetest.core.router.interfaces.ResultParam
import kotlinx.coroutines.flow.Flow
import kotlin.reflect.KClass

interface NavigationManager {
    val savedStateHandle: SavedStateHandle

    fun <Destination : Any> navigate(
        destination: Destination,
        navigationMode: NavigationMode? = null
    )
    fun navigateBack()
    fun <Result : ResultParam> navigateBack(result: Result)
    fun <Result : ResultParam> getResultFlow(resultClass: KClass<Result>): Flow<Result>
    fun isCurrentScreen(destination: Any): Boolean
    suspend fun <Destination : Any> asyncNavigate(
        destination: Destination,
        navigationMode: NavigationMode? = null
    )
    suspend fun asyncNavigateBack()
    suspend fun <Result : ResultParam> asyncNavigateBack(result: Result)
}