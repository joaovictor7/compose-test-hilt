plugins {
    alias(libs.plugins.composeTest.library)
}

android {
    namespace = "com.composetest.common"
}

dependencies {
    api(libs.android.permissions)
}