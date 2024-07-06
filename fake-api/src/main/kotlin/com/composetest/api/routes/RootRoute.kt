package com.composetest.api.routes

import io.ktor.server.application.call
import io.ktor.server.response.respondText
import io.ktor.server.routing.Route
import io.ktor.server.routing.get

fun Route.configureRootRouting() {
    get("/") {
        call.respondText("Hello World!")
    }
}