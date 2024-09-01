package com.composetest.core.router.destinations.home

import com.composetest.core.router.interfaces.Destination
import kotlinx.serialization.Serializable

@Serializable
data class Home3Destination(val teste: String, val r: String) : Destination