package com.composetest.feature.news.data.repositorie

import com.composetest.core.network.util.apiErrorHandler
import com.composetest.feature.news.data.datasource.NewsApiDataSource
import com.composetest.feature.news.data.mapper.ArticleMapper
import com.composetest.feature.news.domain.model.ArticleModel
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