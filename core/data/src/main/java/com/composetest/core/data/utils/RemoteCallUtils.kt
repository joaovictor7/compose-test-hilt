package com.composetest.core.data.utils

import com.composetest.common.enums.FlavorDimension
import com.composetest.common.providers.BuildConfigProvider
import com.composetest.common.providers.DispatcherProvider
import com.composetest.core.data.providers.NetworkProvider
import com.composetest.core.domain.throwables.network.NetworkThrowable
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class RemoteCallUtils @Inject constructor(
    private val networkProvider: NetworkProvider,
    private val buildConfigProvider: BuildConfigProvider,
    private val dispatcherProvider: DispatcherProvider
)  {

    suspend fun <T> executeRemoteCall(onRemoteCall: suspend () -> T): T = when {
        !networkProvider.internetIsConnected -> throw NetworkThrowable()
        else -> call(onRemoteCall)
    }

    private suspend fun <T> call(onRemoteCall: suspend () -> T) =
        withContext(dispatcherProvider.io) {
            if (buildConfigProvider.get.flavorDimension == FlavorDimension.DEVELOP) {
                delay(FAKE_CALL_DELAY)
            }
            onRemoteCall()
        }

    private companion object {
        const val FAKE_CALL_DELAY = 2000L
    }
}