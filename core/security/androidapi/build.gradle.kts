plugins {
    alias(libs.plugins.composetest.android.library)
}

android {
    namespace = "com.composetest.core.security.androidapi"
}

dependencies {
    implementation(projects.core.ui)
    implementation(libs.androidx.biometric)
}