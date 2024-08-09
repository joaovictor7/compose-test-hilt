plugins {
    alias(libs.plugins.composeTest.library)
    alias(libs.plugins.kotlinSerialization)
}

android {
    namespace = "com.composetest.core.data"
}

dependencies {
    implementation(projects.core.database)
    implementation(projects.core.domain)
    implementation(projects.common)
    implementation(libs.kotlin.json.serializable)
    implementation(libs.androidx.dataStore)
    implementation(libs.androidx.hilt.work)
    implementation(libs.ktor.client.android)
    implementation(libs.ktor.client.negotiation)
    implementation(libs.ktor.client.serialization)
    implementation(libs.ktor.client.logging)
    implementation(libs.androidx.work.manager)
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.crashlytics)
    implementation(libs.slf4j.api)
    ksp(libs.androidx.hilt.compiler)
}