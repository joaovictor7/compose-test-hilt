package com.composetest.core.data.utils

import kotlinx.serialization.json.Json

@PublishedApi
internal val jsonConfig = Json {
    isLenient = true
    ignoreUnknownKeys = true
}

@PublishedApi
internal inline fun <reified T> decodeJson(json: String): T = jsonConfig.decodeFromString(json)