package com.composetest.core.network.util

import com.composetest.core.network.model.ApiError
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.http.HttpStatusCode
import kotlinx.io.IOException

inline fun <T> apiErrorHandler(call: () -> T) = runCatching { call() }
    .getOrElse {
        throw when (it) {
            is ClientRequestException -> {
                if (it.response.status == HttpStatusCode.Unauthorized)
                    ApiError.Unauthorized()
                else {
                    ApiError.Request(it.message)
                }
            }
            is ServerResponseException -> ApiError.Request(it.message)
            is IOException -> ApiError.Network()
            else -> ApiError.Unknown(it)
        }
    }