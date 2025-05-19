package com.composetest.feature.news.data.datasource

import com.composetest.feature.news.network.response.NewsApiResponse

internal interface NewsApiDataSource {
    suspend fun getTopHeadlinesNews(): NewsApiResponse
}