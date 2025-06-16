package com.composetest.feature.news.data.datasource.remote

import com.composetest.core.network.di.qualifier.ApiQualifier
import com.composetest.core.network.extension.get
import com.composetest.core.network.util.ApiCallUtils
import com.composetest.feature.news.data.datasource.NewsApiDataSource
import com.composetest.feature.news.network.api.Api
import com.composetest.feature.news.network.response.NewsApiResponse
import io.ktor.client.HttpClient

internal class NewsApiDataSourceImpl(
    private val apiCallUtils: ApiCallUtils,
    @ApiQualifier(Api.NEWS_API) private val newsApi: HttpClient
) : NewsApiDataSource {

    override suspend fun getTopHeadlinesNews() = apiCallUtils.executeApiCall {
        newsApi.get<NewsApiResponse>(TOP_HEADLINE_PATH) {
            url {
                parameters.append("country", "us")
            }
        }
    }

    private companion object {
        const val TOP_HEADLINE_PATH = "top-headlines"
    }
}