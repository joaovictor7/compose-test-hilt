package com.composetest.core.data.datasources.remote

import com.composetest.core.data.utils.RemoteCallUtils

internal class NewsApiFakeDataSourceImpl(
    private val remoteCallUtils: RemoteCallUtils
) : NewsApiDataSource {

    override suspend fun getTopHeadlinesNews() = remoteCallUtils.executeRemoteCall {
        TODO()
    }
}