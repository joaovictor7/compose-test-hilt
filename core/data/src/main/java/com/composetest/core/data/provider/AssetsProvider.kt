package com.composetest.core.data.provider

interface AssetsProvider {

    fun loadJson(jsonFile: String): String
}