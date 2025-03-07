package com.composetest.core.data.providers

interface EnvironmentInstanceProvider {
    fun <Instance> getInstance(instance: Instance, fakeInstance: Instance): Instance
}