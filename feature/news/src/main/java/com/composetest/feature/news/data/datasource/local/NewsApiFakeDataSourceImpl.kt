package com.composetest.feature.news.data.datasource.local

import com.composetest.core.data.extension.readJsonAs
import com.composetest.core.data.provider.AssetsProvider
import com.composetest.core.network.util.ApiCallUtils
import com.composetest.feature.news.data.datasource.NewsApiDataSource
import com.composetest.feature.news.network.response.NewsApiResponse

internal class NewsApiFakeDataSourceImpl(
    private val apiCallUtils: ApiCallUtils,
    private val assetsProvider: AssetsProvider
) : NewsApiDataSource {

    override suspend fun getTopHeadlinesNews() = apiCallUtils.executeApiCall {
        assetsProvider.readJsonAs<NewsApiResponse>("news-api")
    }
}