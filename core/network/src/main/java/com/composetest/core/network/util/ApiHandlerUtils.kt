package com.composetest.core.network.util

import com.composetest.common.api.provider.NetworkProvider
import com.composetest.core.network.model.ApiError
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.http.HttpStatusCode
import javax.inject.Inject

class ApiHandlerUtils @Inject constructor(
    private val networkProvider: NetworkProvider
) {
    suspend fun <T> errorHandler(call: suspend () -> T) = runCatching { call() }
        .getOrElse {
            if (!networkProvider.internetIsConnected) throw ApiError.Network()
            throw when (it) {
                is ClientRequestException -> {
                    if (it.response.status == HttpStatusCode.Unauthorized)
                        ApiError.Unauthorized()
                    else {
                        ApiError.Request(it.message)
                    }
                }
                is ServerResponseException -> ApiError.Request(it.message)
                else -> ApiError.Unknown(it)
            }
        }
}