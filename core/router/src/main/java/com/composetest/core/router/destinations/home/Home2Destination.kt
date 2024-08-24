package com.composetest.core.router.destinations.home

import com.composetest.core.router.interfaces.NavType
import com.composetest.core.router.utils.navType
import kotlinx.serialization.Serializable

@Serializable
data class Home2Destination(val teste: String, val r: String, val inner: InnerHome2) {
    companion object : NavType {
        override val navTypes = mapOf(navType<InnerHome2>())
    }
}

@Serializable
data class InnerHome2(val teste: String, val r: String)