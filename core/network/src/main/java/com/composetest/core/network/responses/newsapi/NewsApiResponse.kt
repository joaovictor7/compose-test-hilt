package com.composetest.core.network.responses.newsapi

import kotlinx.serialization.Serializable

@Serializable
data class NewsApiResponse(
    val articles: List<ArticleResponse>,
)