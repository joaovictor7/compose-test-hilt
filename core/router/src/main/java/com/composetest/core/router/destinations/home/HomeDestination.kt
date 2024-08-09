package com.composetest.core.router.destinations.home

import com.composetest.core.router.destinations.home.navtypes.InnerHome
import com.composetest.core.router.interfaces.NavType
import com.composetest.core.router.utils.navType
import kotlinx.serialization.Serializable

@Serializable
data class HomeDestination(
    val teste: String,
    val innerHome: InnerHome
) {
    companion object : NavType {
        override val navTypes = mapOf(
            navType<InnerHome>()
        )
    }
}