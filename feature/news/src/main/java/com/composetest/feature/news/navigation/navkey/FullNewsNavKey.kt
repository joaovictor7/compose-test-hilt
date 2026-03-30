package com.composetest.feature.news.navigation.navkey

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
internal data class FullNewsNavKey(
    val imageUrl: String?,
    val title: String,
    val description: String?,
    val content: String?
) : NavKey