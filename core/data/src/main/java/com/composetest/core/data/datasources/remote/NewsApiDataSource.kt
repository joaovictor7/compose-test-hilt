package com.composetest.core.data.datasources.remote

import com.composetest.core.data.api.responses.newsapi.NewsApiResponse

internal interface NewsApiDataSource {
    suspend fun getTopHeadlinesNews(): NewsApiResponse
}