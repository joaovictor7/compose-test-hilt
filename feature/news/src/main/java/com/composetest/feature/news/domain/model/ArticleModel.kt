package com.composetest.feature.news.domain.model

import java.time.LocalDateTime

internal data class ArticleModel(
    val provider: String,
    val title: String,
    val description: String?,
    val urlToImage: String?,
    val publishedAt: LocalDateTime,
    val content: String?
)