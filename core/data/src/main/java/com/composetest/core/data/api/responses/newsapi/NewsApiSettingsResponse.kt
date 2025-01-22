package com.composetest.core.data.api.responses.newsapi

import kotlinx.serialization.Serializable

@Serializable
internal data class NewsApiSettingsResponse(val apiKey: String, val url: String)