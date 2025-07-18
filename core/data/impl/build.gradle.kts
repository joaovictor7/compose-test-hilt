plugins {
    alias(libs.plugins.composeTest.library)
}

android {
    namespace = "com.composetest.core.data.impl"
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.common)
    implementation(projects.core.data.api)
    implementation(projects.core.data.androidapi)
    implementation(projects.common)
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.config)
    implementation(libs.androidx.work.manager)
    implementation(libs.androidx.dataStore)
}