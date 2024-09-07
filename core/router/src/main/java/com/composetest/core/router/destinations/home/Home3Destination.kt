package com.composetest.core.router.destinations.home

import com.composetest.core.router.interfaces.Destination
import com.composetest.core.router.interfaces.NavType
import com.composetest.core.router.utils.navType
import kotlinx.serialization.Serializable

@Serializable
data class Home3Destination(val teste: String, val r: String, val e: InnerHome) : Destination {
    companion object : NavType {
        override val navTypes = mapOf(navType<InnerHome>())
    }
}

@Serializable
data class InnerHome(val teste: String)