plugins {
    alias(libs.plugins.composeTest.library)
}

android {
    namespace = "com.composetest.core.data.androidapi"
}

dependencies {
    implementation(libs.androidx.work.manager)
    api(libs.androidx.dataStore)
}