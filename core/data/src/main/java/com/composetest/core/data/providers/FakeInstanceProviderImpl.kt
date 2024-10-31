package com.composetest.core.data.providers

import com.composetest.common.enums.FlavorDimension
import com.composetest.common.providers.BuildConfigProvider
import javax.inject.Inject

internal class FakeInstanceProviderImpl @Inject constructor(
    private val buildConfigProvider: BuildConfigProvider
) : FakeInstanceProvider {

    override fun <Instance> getInstance(
        instance: Instance,
        fakeInstance: Instance
    ) = if (buildConfigProvider.get.flavorDimension == FlavorDimension.DEVELOP) {
        fakeInstance
    } else {
        instance
    }
}