package com.composetest.core.data.impl.provider

import com.composetest.common.api.provider.DispatcherProvider
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

internal class DispatcherProviderImpl @Inject constructor() : DispatcherProvider {
    override val io = Dispatchers.IO
    override val main = Dispatchers.Main
    override val default = Dispatchers.Default
}