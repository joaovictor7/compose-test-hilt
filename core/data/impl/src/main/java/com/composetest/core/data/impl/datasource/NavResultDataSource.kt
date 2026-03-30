package com.composetest.core.data.impl.datasource

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import java.util.concurrent.ConcurrentHashMap
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.reflect.KClass

@Singleton
internal class NavResultDataSource @Inject constructor() {

    private val channels = ConcurrentHashMap<String, Channel<Any>>()

    suspend fun <T : Any> set(value: T) {
        getChannel(value.javaClass.name).send(value)
    }

    fun <T : Any> observe(type: KClass<T>): Flow<T> =
        getChannel(type.java.name).receiveAsFlow() as Flow<T>

    private fun getChannel(key: String): Channel<Any> =
        channels.getOrPut(key) { Channel(Channel.BUFFERED) }
}
