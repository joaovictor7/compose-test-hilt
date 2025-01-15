package com.composetest.core.data.utils

import com.composetest.common.enums.Flavor
import com.composetest.common.providers.BuildConfigProvider
import com.composetest.common.providers.DispatcherProvider
import com.composetest.core.domain.errors.ApiError
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import kotlinx.io.IOException
import javax.inject.Inject

internal class ApiCallUtils @Inject constructor(
    private val buildConfigProvider: BuildConfigProvider,
    private val dispatcherProvider: DispatcherProvider
) {

    suspend fun <T> executeApiCall(onRemoteCall: suspend () -> T) = runCatching {
        call { onRemoteCall() }
    }.getOrElse {
        throw errorHandler(it)
    }

    private suspend fun <T> call(onRemoteCall: suspend () -> T) =
        withContext(dispatcherProvider.io) {
            if (buildConfigProvider.get.flavor == Flavor.DEVELOP) {
                delay(FAKE_CALL_DELAY)
            }
            onRemoteCall()
        }

    private fun errorHandler(error: Throwable) = when (error) {
        is ClientRequestException -> {
            if (error.response.status == HttpStatusCode.Unauthorized)
                ApiError.Unauthorized()
            else {
                ApiError.Request(error.message)
            }
        }
        is ServerResponseException -> ApiError.Request(error.message)
        is IOException -> ApiError.Network()
        else -> ApiError.Unknown(error)
    }

    private companion object {
        const val FAKE_CALL_DELAY = 2000L
    }
}