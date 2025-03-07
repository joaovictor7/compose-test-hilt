package com.composetest.core.data.extensions

import com.composetest.core.data.providers.AssetsProvider
import com.composetest.core.data.utils.decodeJson

inline fun <reified T> AssetsProvider.readJsonAs(jsonFile: String): T {
    val json = loadJson(jsonFile)
    return decodeJson<T>(json)
}