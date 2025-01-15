package com.composetest.core.data.datasources.local

import com.composetest.core.data.api.responses.newsapi.NewsApiResponse
import com.composetest.core.data.datasources.NewsApiDataSource
import com.composetest.core.data.providers.AssetsProvider
import com.composetest.core.data.utils.ApiCallUtils

internal class NewsApiFakeDataSourceImpl(
    private val apiCallUtils: ApiCallUtils,
    private val assetsProvider: AssetsProvider
) : NewsApiDataSource {

    override suspend fun getTopHeadlinesNews() = apiCallUtils.executeApiCall {
        assetsProvider.getObjectFromJson<NewsApiResponse>("news-api")
    }
}