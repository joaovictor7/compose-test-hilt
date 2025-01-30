package com.composetest.core.domain.enums

enum class Flavor {
    DEVELOP, STAGING, PRODUCTION;

    override fun toString() = name.lowercase()

    companion object {
        fun String.getFlavor() = entries.find { it.toString() == this } ?: DEVELOP
    }
}