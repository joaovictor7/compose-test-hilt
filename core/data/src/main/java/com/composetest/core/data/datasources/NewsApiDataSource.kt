package com.composetest.core.data.datasources

import com.composetest.core.network.responses.newsapi.NewsApiResponse

internal interface NewsApiDataSource {
    suspend fun getTopHeadlinesNews(): NewsApiResponse
}