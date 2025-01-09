package com.composetest.core.data.api.responses.newsapi

import kotlinx.serialization.Serializable

@Serializable
internal data class NewsApiResponse(
    val articles: List<ArticleResponse>,
)