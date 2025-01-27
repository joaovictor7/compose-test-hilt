package com.composetest.core.data.utils

import com.composetest.common.enums.Flavor
import com.composetest.common.providers.BuildConfigProvider
import com.composetest.common.providers.DispatcherProvider
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

internal class ApiCallUtils @Inject constructor(
    private val buildConfigProvider: BuildConfigProvider,
    private val dispatcherProvider: DispatcherProvider
) {

    suspend fun <T> executeApiCall(onRemoteCall: suspend () -> T) =
        withContext(dispatcherProvider.io) {
            if (buildConfigProvider.get.flavor == Flavor.DEVELOP) {
                delay(fakeCallDelay)
            }
            onRemoteCall()
        }

    private companion object {
        val fakeCallDelay = 2.seconds
    }
}