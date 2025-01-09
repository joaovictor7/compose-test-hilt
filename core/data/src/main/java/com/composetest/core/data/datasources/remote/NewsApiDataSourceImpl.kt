package com.composetest.core.data.datasources.remote

import com.composetest.core.data.api.responses.newsapi.NewsApiResponse
import com.composetest.core.data.di.qualifiers.ApiQualifier
import com.composetest.core.data.enums.Api
import com.composetest.core.data.extensions.get
import com.composetest.core.data.utils.RemoteCallUtils
import io.ktor.client.HttpClient

internal class NewsApiDataSourceImpl(
    private val remoteCallUtils: RemoteCallUtils,
    @ApiQualifier(Api.NEWS_API) private val newsApi: HttpClient
) : NewsApiDataSource {

    override suspend fun getTopHeadlinesNews() = remoteCallUtils.executeRemoteCall {
        newsApi.get<NewsApiResponse>(TOP_HEADLINE_PATH)
    }

    private companion object {
        const val TOP_HEADLINE_PATH = "top-headlines"
    }
}