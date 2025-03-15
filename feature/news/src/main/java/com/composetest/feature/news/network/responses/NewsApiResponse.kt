package com.composetest.feature.news.network.responses

import kotlinx.serialization.Serializable

@Serializable
internal data class NewsApiResponse(
    val articles: List<ArticleResponse>,
)