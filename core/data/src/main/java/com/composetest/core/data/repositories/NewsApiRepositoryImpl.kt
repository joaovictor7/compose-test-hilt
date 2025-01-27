package com.composetest.core.data.repositories

import com.composetest.core.data.datasources.NewsApiDataSource
import com.composetest.core.data.mappers.ArticleMapper
import com.composetest.core.data.utils.apiErrorHandler
import com.composetest.core.domain.models.ArticleModel
import com.composetest.core.domain.repositories.NewsApiRepository
import javax.inject.Inject

internal class NewsApiRepositoryImpl @Inject constructor(
    private val newsApiDataSource: NewsApiDataSource,
    private val articleMapper: ArticleMapper,
) : NewsApiRepository {

    override suspend fun getTopHeadlinesNews(): List<ArticleModel> {
        val response = apiErrorHandler { newsApiDataSource.getTopHeadlinesNews() }
        return articleMapper(response)
    }
}