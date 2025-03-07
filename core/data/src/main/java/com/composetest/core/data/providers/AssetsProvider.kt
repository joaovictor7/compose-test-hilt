package com.composetest.core.data.providers

interface AssetsProvider {

    fun loadJson(jsonFile: String): String
}