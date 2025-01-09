package com.composetest.core.router.destinations.news

import com.composetest.core.router.interfaces.Destination
import kotlinx.serialization.Serializable

@Serializable
data class FullNewsDestination(
    val imageUrl: String?,
    val title: String,
    val description: String?,
    val content: String?
) : Destination
