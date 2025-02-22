package com.composetest.core.data.utils

import kotlinx.serialization.json.Json

private val jsonConfig = Json {
    isLenient = true
    ignoreUnknownKeys = true
}

internal inline fun <reified T> decodeJson(json: String): T = jsonConfig.decodeFromString(json)