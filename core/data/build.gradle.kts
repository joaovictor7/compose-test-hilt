plugins {
    alias(libs.plugins.composeTest.library)
    alias(libs.plugins.kotlinSerialization)
}

android {
    namespace = "com.composetest.core.data"
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.common)
    implementation(libs.androidx.work.manager)
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.config)
    implementation(libs.kotlin.json.serializable)
    api(libs.androidx.dataStore)
}