plugins {
    alias(libs.plugins.composetest.kotlin)
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.common.api)
    implementation(libs.ktor.client.negotiation)
    implementation(libs.ktor.client.serialization)
    implementation(libs.ktor.client.logging)
    implementation(libs.slf4j.api)
    api(libs.ktor.client.android)
}