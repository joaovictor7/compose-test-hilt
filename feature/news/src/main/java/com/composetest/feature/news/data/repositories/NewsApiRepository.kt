package com.composetest.feature.news.data.repositories

import com.composetest.core.data.utils.apiErrorHandler
import com.composetest.feature.news.data.datasources.NewsApiDataSource
import com.composetest.feature.news.data.mappers.ArticleMapper
import com.composetest.feature.news.domain.models.ArticleModel
import javax.inject.Inject

internal class NewsApiRepository @Inject constructor(
    private val newsApiDataSource: NewsApiDataSource,
    private val articleMapper: ArticleMapper,
) {

    suspend fun getTopHeadlinesNews(): List<ArticleModel> {
        val response = apiErrorHandler { newsApiDataSource.getTopHeadlinesNews() }
        return articleMapper.mapperToModels(response)
    }
}