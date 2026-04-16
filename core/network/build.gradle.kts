plugins {
    alias(libs.plugins.composetest.kotlin.library)
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.common.api)
    implementation(libs.ktor.client.negotiation)
    implementation(libs.ktor.client.serialization)
    implementation(libs.ktor.client.logging)
    api(libs.ktor.client.android)
}