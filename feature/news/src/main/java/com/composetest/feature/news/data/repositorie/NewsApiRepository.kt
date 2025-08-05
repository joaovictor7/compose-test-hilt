package com.composetest.feature.news.data.repositorie

import com.composetest.core.network.util.ApiHandlerUtils
import com.composetest.feature.news.data.datasource.NewsApiDataSource
import com.composetest.feature.news.data.mapper.ArticleMapper
import com.composetest.feature.news.domain.model.ArticleModel
import javax.inject.Inject

internal class NewsApiRepository @Inject constructor(
    private val apiHandlerUtils: ApiHandlerUtils,
    private val newsApiDataSource: NewsApiDataSource,
    private val articleMapper: ArticleMapper,
) {

    suspend fun getTopHeadlinesNews(): List<ArticleModel> {
        val response = apiHandlerUtils.errorHandler { newsApiDataSource.getTopHeadlinesNews() }
        return articleMapper.mapperToModels(response)
    }
}