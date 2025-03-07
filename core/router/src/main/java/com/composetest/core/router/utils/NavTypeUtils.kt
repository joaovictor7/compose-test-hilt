package com.composetest.core.router.utils

import android.os.Bundle
import androidx.navigation.NavType
import kotlinx.serialization.json.Json
import kotlin.reflect.KType
import kotlin.reflect.full.companionObjectInstance
import kotlin.reflect.typeOf
import com.composetest.core.router.interfaces.NavType as NavTypes

inline fun <reified D> getNavTypes(): Map<KType, NavType<*>> =
    (D::class.companionObjectInstance as? NavTypes)?.navTypes ?: emptyMap()

//internal inline fun <reified T> navType(
//    isNullableAllowed: Boolean = false
//): Pair<KType, NavType<T>> =
//    typeOf<T>() to object : NavType<T>(isNullableAllowed) {
//        override fun get(bundle: Bundle, key: String): T? =
//            bundle.getString(key)?.let { Json.decodeFromString<T>(it) }
//
//        override fun parseValue(value: String) = Json.decodeFromString<T>(value)
//
//        override fun serializeAsValue(value: T): String = Json.encodeToString(value)
//
//        override fun put(bundle: Bundle, key: String, value: T) {
//            bundle.putString(key, Json.encodeToString(value))
//        }
//    }