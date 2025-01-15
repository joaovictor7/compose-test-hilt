package com.composetest.core.data.providers

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.serialization.json.Json
import javax.inject.Inject

internal class AssetsProvider @Inject constructor(
    @ApplicationContext private val context: Context
) {

    private val jsonBuilder = Json {
        ignoreUnknownKeys = true
    }

    inline fun <reified T> getObjectFromJson(jsonFile: String): T {
        val json = loadJson(jsonFile)
        return jsonBuilder.decodeFromString<T>(json)
    }

    private fun loadJson(jsonFile: String): String {
        val input = context.assets.open("$jsonFile.json")
        val buffer = ByteArray(input.available())
        input.read(buffer)
        input.close()
        return String(buffer)
    }
}