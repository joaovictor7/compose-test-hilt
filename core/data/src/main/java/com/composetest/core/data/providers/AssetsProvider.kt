package com.composetest.core.data.providers

import android.content.Context
import com.composetest.core.data.utils.decodeJson
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

internal class AssetsProvider @Inject constructor(
    @ApplicationContext private val context: Context
) {

    inline fun <reified T> readJsonAs(jsonFile: String): T {
        val json = loadJson(jsonFile)
        return decodeJson<T>(json)
    }

    private fun loadJson(jsonFile: String): String {
        val input = context.assets.open("$jsonFile.json")
        val buffer = ByteArray(input.available())
        input.read(buffer)
        input.close()
        return String(buffer)
    }
}