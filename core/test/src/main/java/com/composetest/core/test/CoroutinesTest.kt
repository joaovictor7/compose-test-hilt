package com.composetest.core.test

import com.composetest.core.test.extensions.CoroutinesExtension
import kotlinx.coroutines.test.TestDispatcher
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(CoroutinesExtension::class)
interface CoroutinesTest {
    var testDispatcher: TestDispatcher
}