plugins {
    alias(libs.plugins.composeTest.library)
}

android {
    namespace = "com.composetest.core.data"
}

dependencies {
    implementation(projects.core.database)
    implementation(projects.core.domain)
    implementation(projects.core.network)
    implementation(projects.core.security)
    implementation(projects.common)
    implementation(projects.core.analytic)
    implementation(libs.kotlin.json.serializable)
    implementation(libs.androidx.hilt.work)
    implementation(libs.androidx.work.manager)
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.crashlytics)
    implementation(libs.firebase.config)
    api(libs.androidx.dataStore)
    ksp(libs.androidx.hilt.compiler)
}