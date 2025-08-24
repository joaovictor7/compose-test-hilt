package com.composetest.core.router.destination.findroute

import com.composetest.core.router.interfaces.Destination
import kotlinx.serialization.Serializable

@Serializable
data class FindRouteDestination(
    val filter: String? = null,
) : Destination