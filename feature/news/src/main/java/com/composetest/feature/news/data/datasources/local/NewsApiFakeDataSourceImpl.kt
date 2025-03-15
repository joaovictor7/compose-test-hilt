package com.composetest.feature.news.data.datasources.local

import com.composetest.core.data.extensions.readJsonAs
import com.composetest.core.data.providers.AssetsProvider
import com.composetest.core.data.utils.ApiCallUtils
import com.composetest.feature.news.data.datasources.NewsApiDataSource
import com.composetest.feature.news.network.responses.NewsApiResponse

internal class NewsApiFakeDataSourceImpl(
    private val apiCallUtils: ApiCallUtils,
    private val assetsProvider: AssetsProvider
) : NewsApiDataSource {

    override suspend fun getTopHeadlinesNews() = apiCallUtils.executeApiCall {
        assetsProvider.readJsonAs<NewsApiResponse>("news-api")
    }
}