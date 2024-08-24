package com.composetest.common.providers

import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

internal class DispatcherProviderImpl @Inject constructor() : DispatcherProvider {
    override val io = Dispatchers.IO
    override val main = Dispatchers.Main
    override val default = Dispatchers.Default
}