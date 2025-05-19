package com.composetest.core.test.extension

import com.composetest.core.test.CoroutinesTest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.extension.AfterEachCallback
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.TestInstancePostProcessor

@OptIn(ExperimentalCoroutinesApi::class)
internal class CoroutinesExtension :
    TestInstancePostProcessor, BeforeEachCallback, AfterEachCallback {

    private val testDispatcher = UnconfinedTestDispatcher()

    override fun postProcessTestInstance(testInstance: Any?, context: ExtensionContext?) {
        (testInstance as? CoroutinesTest)?.let { coroutineTest ->
            coroutineTest.testDispatcher = testDispatcher
        }
    }

    override fun beforeEach(context: ExtensionContext?) {
        Dispatchers.setMain(testDispatcher)
    }

    override fun afterEach(context: ExtensionContext?) {
        Dispatchers.resetMain()
    }
}