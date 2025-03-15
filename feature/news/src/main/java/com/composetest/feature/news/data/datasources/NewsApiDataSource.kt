package com.composetest.feature.news.data.datasources

import com.composetest.feature.news.network.responses.NewsApiResponse

internal interface NewsApiDataSource {
    suspend fun getTopHeadlinesNews(): NewsApiResponse
}