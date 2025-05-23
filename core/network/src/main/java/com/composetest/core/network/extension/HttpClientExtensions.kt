package com.composetest.core.network.extension

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.post

suspend inline fun <reified Response> HttpClient.post(
    path: String,
    request: HttpRequestBuilder.() -> Unit
) = post(path, request)

suspend inline fun <reified Response> HttpClient.post(
    request: HttpRequestBuilder.() -> Unit
) = post(request)

suspend inline fun <reified Response> HttpClient.get(
    path: String,
    request: HttpRequestBuilder.() -> Unit = {}
) = get(path, request).body<Response>()

suspend inline fun <reified Response> HttpClient.get(
    request: HttpRequestBuilder.() -> Unit = {}
) = get(request).body<Response>()