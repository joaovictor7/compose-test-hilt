package com.composetest.core.data.datasources.local

import com.composetest.core.data.datasources.NewsApiDataSource
import com.composetest.core.data.extensions.readJsonAs
import com.composetest.core.data.providers.AssetsProvider
import com.composetest.core.data.utils.ApiCallUtils
import com.composetest.core.network.responses.newsapi.NewsApiResponse

internal class NewsApiFakeDataSourceImpl(
    private val apiCallUtils: ApiCallUtils,
    private val assetsProvider: AssetsProvider
) : NewsApiDataSource {

    override suspend fun getTopHeadlinesNews() = apiCallUtils.executeApiCall {
        assetsProvider.readJsonAs<NewsApiResponse>("news-api")
    }
}