package com.composetest.core.data.api.responses.newsapi

import kotlinx.serialization.Serializable

@Serializable
internal data class ArticleResponse(
    val source: SourceArticleResponse,
    val title: String,
    val description: String?,
    val urlToImage: String?,
    val publishedAt: String,
    val content: String?
)
