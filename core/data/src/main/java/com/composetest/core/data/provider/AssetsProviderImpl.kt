package com.composetest.core.data.provider

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

internal class AssetsProviderImpl @Inject constructor(
    @param:ApplicationContext private val context: Context
) : AssetsProvider {

    override fun loadJson(jsonFile: String): String {
        val input = context.assets.open("$jsonFile.json")
        val buffer = ByteArray(input.available())
        input.read(buffer)
        input.close()
        return String(buffer)
    }
}