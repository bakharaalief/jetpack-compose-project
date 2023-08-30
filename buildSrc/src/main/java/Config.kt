import org.gradle.api.JavaVersion

object Config {
    const val compileSdk = 33
    const val minSdk = 31
    const val targetSdk = minSdk
    const val versionCode = 1
    const val versionName = "1.0.0"
    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    val sourceCompatibility = JavaVersion.VERSION_1_8
    val targetCompatibility = JavaVersion.VERSION_1_8
    const val jvmTarget = "1.8"
    const val kotlinCompilerExtensionVersion = "1.4.3"
}