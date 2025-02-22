package com.composetest.core.network.responses.newsapi

import kotlinx.serialization.Serializable

@Serializable
data class ArticleResponse(
    val source: SourceArticleResponse,
    val title: String,
    val description: String?,
    val urlToImage: String?,
    val publishedAt: String,
    val content: String?
)
