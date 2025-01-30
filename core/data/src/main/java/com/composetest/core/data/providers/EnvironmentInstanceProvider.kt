package com.composetest.core.data.providers

import com.composetest.core.domain.enums.Flavor
import com.composetest.core.domain.providers.BuildConfigProvider
import javax.inject.Inject

internal class EnvironmentInstanceProvider @Inject constructor(
    private val buildConfigProvider: BuildConfigProvider
) {

    fun <Instance> getInstance(instance: Instance, fakeInstance: Instance) =
        if (buildConfigProvider.get.flavor == Flavor.DEVELOP) fakeInstance else instance
}