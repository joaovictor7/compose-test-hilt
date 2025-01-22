package com.composetest.core.data.utils

import kotlinx.serialization.json.Json

val jsonConfig = Json {
    prettyPrint = true
    isLenient = true
    ignoreUnknownKeys = true
}

inline fun <reified T> decodeJson(json: String): T = jsonConfig.decodeFromString(json)