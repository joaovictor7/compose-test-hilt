package com.composetest.core.data.api.extension

import com.composetest.core.data.api.provider.AssetsProvider
import com.composetest.core.data.api.util.decodeJson

inline fun <reified T> AssetsProvider.readJsonAs(jsonFile: String): T {
    val json = loadJson(jsonFile)
    return decodeJson<T>(json)
}