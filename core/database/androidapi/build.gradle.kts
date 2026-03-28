plugins {
    alias(libs.plugins.composetest.android.library)
}

android {
    namespace = "com.composetest.core.database.androidapi"
}

dependencies {
    implementation(libs.room.kotlin)
}