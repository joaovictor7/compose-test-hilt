package com.composetest.feature.news.data.mapper

import com.composetest.feature.news.domain.model.ArticleModel
import com.composetest.feature.news.network.response.NewsApiResponse
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

internal class ArticleMapper @Inject constructor() {
    fun mapperToModels(response: NewsApiResponse) =
        response.articles.map {
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