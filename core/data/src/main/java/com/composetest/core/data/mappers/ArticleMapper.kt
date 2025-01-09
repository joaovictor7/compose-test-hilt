package com.composetest.core.data.mappers

import com.composetest.core.data.api.responses.newsapi.NewsApiResponse
import com.composetest.core.domain.models.ArticleModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

internal class ArticleMapper @Inject constructor() {
    operator fun invoke(response: NewsApiResponse) = response.articles.map {
        ArticleModel(
            provider = it.source.name,
            title = it.title,
            description = it.description,
            urlToImage = it.urlToImage,
            publishedAt = LocalDateTime.parse(it.publishedAt, formatter),
            content = it.content
        )
    }

    private companion object {
        val formatter: DateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME
    }
}