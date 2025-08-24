plugins {
    alias(libs.plugins.composetest.android.library)
    alias(libs.plugins.composetest.test)
}

android {
    namespace = "com.composetest.core.data.impl"
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.common.api)
    implementation(projects.core.data.api)
    implementation(projects.core.data.androidapi)
    implementation(projects.common.api)
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.config)
    implementation(libs.androidx.work.manager)
    implementation(libs.androidx.dataStore)
}