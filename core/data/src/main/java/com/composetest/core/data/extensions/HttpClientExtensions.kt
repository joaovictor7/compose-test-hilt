package com.composetest.core.data.extensions

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.post

internal suspend inline fun <reified Response> HttpClient.post(
    path: String,
    request: HttpRequestBuilder.() -> Unit
) = post(path, request)

internal suspend inline fun <reified Response> HttpClient.post(
    request: HttpRequestBuilder.() -> Unit
) = post(request)

internal suspend inline fun <reified Response> HttpClient.get(
    path: String,
    request: HttpRequestBuilder.() -> Unit = {}
) = get(path, request).body<Response>()

internal suspend inline fun <reified Response> HttpClient.get(
    request: HttpRequestBuilder.() -> Unit = {}
) = get(request).body<Response>()