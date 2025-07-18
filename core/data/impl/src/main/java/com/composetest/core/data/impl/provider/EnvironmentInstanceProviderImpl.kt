package com.composetest.core.data.impl.provider

import com.composetest.core.data.api.provider.EnvironmentInstanceProvider
import com.composetest.core.domain.enums.Flavor
import com.composetest.core.domain.provider.BuildConfigProvider
import javax.inject.Inject

internal class EnvironmentInstanceProviderImpl @Inject constructor(
    private val buildConfigProvider: BuildConfigProvider
) : EnvironmentInstanceProvider {

    override fun <Instance> getInstance(instance: Instance, fakeInstance: Instance) =
        if (buildConfigProvider.buildConfig.flavor == Flavor.DEVELOP) fakeInstance else instance
}