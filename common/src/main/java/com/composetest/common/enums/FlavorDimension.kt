package com.composetest.common.enums

enum class FlavorDimension {
    DEVELOP, STAGING, PRODUCTION;

    override fun toString() = name.lowercase()

    companion object {
        fun String.getDimensionFlavor() = entries.find { it.toString() == this } ?: DEVELOP
    }
}