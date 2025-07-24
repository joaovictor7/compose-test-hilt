plugins {
    alias(libs.plugins.composetest.library)
}

android {
    namespace = "com.composetest.core.analytic.impl"
}

dependencies {
    implementation(projects.core.analytic.api)
    implementation(projects.core.domain)
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.crashlytics)
}