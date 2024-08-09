package com.composetest.core.router.managers

import androidx.lifecycle.SavedStateHandle
import com.composetest.core.router.enums.NavigationMode
import com.composetest.core.router.interfaces.ResultParam

interface NavigationManager {
    val savedStateHandle: SavedStateHandle

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
}