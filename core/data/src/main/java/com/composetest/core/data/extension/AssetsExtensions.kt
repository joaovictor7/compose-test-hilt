package com.composetest.core.data.extension

import com.composetest.core.data.provider.AssetsProvider
import com.composetest.core.data.util.decodeJson

inline fun <reified T> AssetsProvider.readJsonAs(jsonFile: String): T {
    val json = loadJson(jsonFile)
    return decodeJson<T>(json)
}