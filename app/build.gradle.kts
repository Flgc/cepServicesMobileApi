plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.services"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.services"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    /*
       Library installation - 14-9-25 - https://square.github.io/retrofit/
       - attention downgrade for version implementation 3.1.0-SNAPSHOT
     */
    implementation("com.squareup.retrofit2:retrofit:2.11.0")

    /*
      Library install - 14-9-25 - https://square.github.io/retrofit/configuration/
      - attention downgrade for version implementation
    */

    implementation("com.squareup.retrofit2:converter-gson:2.11.0")
}