package com.composetest.core.data.api.provider

interface EnvironmentInstanceProvider {
    fun <Instance> getInstance(instance: Instance, fakeInstance: Instance): Instance
}