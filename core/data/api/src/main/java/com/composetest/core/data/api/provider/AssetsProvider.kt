package com.composetest.core.data.api.provider

interface AssetsProvider {

    fun loadJson(jsonFile: String): String
}