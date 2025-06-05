plugins {
    alias(libs.plugins.composeTest.library)
}

android {
    namespace = "com.composetest.core.analytic"
}

dependencies {
    implementation(projects.core.domain)
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.crashlytics)
}