package com.composetest.core.data.datasources.remote

import com.composetest.core.data.api.responses.newsapi.NewsApiResponse
import com.composetest.core.data.datasources.NewsApiDataSource
import com.composetest.core.data.di.qualifiers.ApiQualifier
import com.composetest.core.data.extensions.get
import com.composetest.core.data.utils.ApiCallUtils
import com.composetest.core.domain.enums.Api
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