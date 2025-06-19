package com.composetest.feature.news.navigation.destination

import com.composetest.core.router.interfaces.Destination
import kotlinx.serialization.Serializable

@Serializable
internal data class FullNewsDestination(
    val imageUrl: String?,
    val title: String,
    val description: String?,
    val content: String?
) : Destination