plugins {
    alias(libs.plugins.composeTest.library)
}

android {
    namespace = "com.composetest.core.data"
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.common)
    implementation(projects.core.analytic)
    implementation(libs.androidx.work.manager)
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.crashlytics)
    implementation(libs.firebase.config)
    api(libs.androidx.dataStore)
}