package com.composetest.feature.news.network.response

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
