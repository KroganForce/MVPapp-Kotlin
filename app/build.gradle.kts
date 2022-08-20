plugins {
    kotlin("android")
    kotlin("kapt")
    id("com.android.application")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    compileSdk = AppConfig.compileSdkVersion
    buildToolsVersion = AppConfig.buildToolsVersion

    kapt {
        generateStubs = true
        correctErrorTypes = true
    }
    defaultConfig {
        applicationId = "com.exampleapp.mvpapp_kotlin"
        minSdk = AppConfig.minSdkVersion
        targetSdk = AppConfig.targetSdkVersion
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName
        testInstrumentationRunner = AppConfig.testRunner
    }
    testOptions {
        packagingOptions {
            jniLibs {
                useLegacyPackaging = true
            }
        }
    }
    buildFeatures {
        viewBinding = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "$project.rootDir/tools/proguard-rules-debug.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = AppConfig.sourceCompatibilityVersion
        targetCompatibility = AppConfig.targetCompatibilityVersion
    }
    kotlinOptions {
        jvmTarget = AppConfig.jvmTargetVersion
    }
}

dependencies {

    implementation(Dependencies.kotlinStdLib)
    implementation(Dependencies.androidCoreKtx)
    implementation(Dependencies.lifecycleViewModelKtx)
    implementation(Dependencies.androidxAppCompat)
    implementation(Dependencies.androidMaterial)
    implementation(Dependencies.constraintLayout)
    implementation(Dependencies.navigationFragment)
    implementation(Dependencies.navigationUi)
    implementation(Dependencies.lifecycleLiveData)

    //Hilt
    implementation(Dependencies.hiltAndroid)
    kapt(Dependencies.daggerHiltCompiler)

    //Hilt for tests
    testImplementation(Dependencies.hiltTesting)
    androidTestImplementation(Dependencies.hiltAndroidTesting)
    kaptTest(Dependencies.hiltCompiler)
    kaptAndroidTest(Dependencies.hiltCompiler)

    testAnnotationProcessor(Dependencies.hiltCompiler)
    androidTestAnnotationProcessor(Dependencies.hiltCompiler)

    // Room
    implementation(Dependencies.roomRuntime)
    kapt(Dependencies.roomCompiler)
    implementation(Dependencies.roomKtx)
    //Coroutines
    implementation(Dependencies.coroutinesCore)

    //Test
    testImplementation(Dependencies.junit)
    testImplementation(Dependencies.archCoreTesting)
    testImplementation(Dependencies.coroutinesAndroid)
    testImplementation(Dependencies.coroutinesTest)
    testImplementation(Dependencies.mockk)
    testImplementation(Dependencies.junitKtx)
    testImplementation(Dependencies.testCoreKtx)
    testImplementation(Dependencies.robolectric)
    testImplementation(Dependencies.hamcrest)

    androidTestImplementation(Dependencies.androidTestCore)
    androidTestImplementation(Dependencies.mockkAndroid)
    androidTestImplementation(Dependencies.archCoreTesting)
    androidTestImplementation(Dependencies.coroutinesAndroidTest) {
        exclude(group = "org.jetbrains.kotlinx", module = "kotlinx-coroutines-debug")
    }
    androidTestImplementation(Dependencies.androidJunit)
    androidTestImplementation(Dependencies.androidTestRunner)
    androidTestImplementation(Dependencies.androidTestRules)
    androidTestImplementation(Dependencies.androidNavigationTesting)

    // Espresso dependencies
    androidTestImplementation(Dependencies.espressoCore)
    androidTestImplementation(Dependencies.espressoContrib)
    androidTestImplementation(Dependencies.espressoIntents)
    androidTestImplementation(Dependencies.espressoAccessibility)
    androidTestImplementation(Dependencies.espressoWeb)
    androidTestImplementation(Dependencies.espressoIdling)

    // https://issuetracker.google.com/128612536 is fixed this can be fixed.
    debugImplementation(Dependencies.debugFragmentTesting)

    // The following Espresso dependency can be either "implementation",
    // or "androidTestImplementation", depending on whether you want the
    // dependency to appear on your APK’"s compile classpath or the test APK
    // classpath.
    androidTestImplementation(Dependencies.espressoIdlingResource)
}
