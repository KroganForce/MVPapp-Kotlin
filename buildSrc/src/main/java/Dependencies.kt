import Versions.androidMaterialVersion
import Versions.androidNavigationTestingVersion
import Versions.androidTestCoreVersion
import Versions.androidTestRulesVersion
import Versions.androidTestRunnerVersion
import Versions.androidxAppCompatVersion
import Versions.androidxCoreVersion
import Versions.archCoreTestingVersion
import Versions.constraintLayoutVersion
import Versions.coroutinesVersion
import Versions.espressoCoreVersion
import Versions.fragmentTestingVersion
import Versions.hamcrestVersion
import Versions.hiltVersion
import Versions.junitKtxVersion
import Versions.junitVersion
import Versions.kotlinVersion
import Versions.lifecycleVersion
import Versions.lifecycleViewModelKtxVersion
import Versions.mockkVersion
import Versions.navigationFragmentVersion
import Versions.navigationUiVersion
import Versions.robolectricVersion
import Versions.roomVersion
import Versions.testCoreKtxVersion
import org.gradle.api.JavaVersion

object Dependencies {
    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib:${
        kotlinVersion
    }"
    const val androidCoreKtx = "androidx.core:core-ktx:${
        androidxCoreVersion
    }"
    const val lifecycleViewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${
        lifecycleViewModelKtxVersion
    }"
    const val androidxAppCompat = "androidx.appcompat:appcompat:${
        androidxAppCompatVersion
    }"
    const val androidMaterial = "com.google.android.material:material:${
        androidMaterialVersion
    }"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${
        constraintLayoutVersion
    }"
    const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${
        navigationFragmentVersion
    }"
    const val navigationUi = "androidx.navigation:navigation-ui-ktx:${
        navigationUiVersion
    }"
    const val lifecycleLiveData = "androidx.lifecycle:lifecycle-livedata-ktx:${
        lifecycleVersion
    }"

    //Hilt
    const val hiltAndroid = "com.google.dagger:hilt-android:${
        hiltVersion
    }"
    const val hiltCompiler = "com.google.dagger:hilt-android-compiler:${
        hiltVersion
    }"
    const val daggerHiltCompiler = "com.google.dagger:hilt-compiler:${
        hiltVersion
    }"

    //Hilt for tests
    const val hiltTesting = "com.google.dagger:hilt-android-testing:${
        hiltVersion
    }"
    const val hiltAndroidTesting = "com.google.dagger:hilt-android-testing:${
        hiltVersion
    }"

    //Room
    const val roomRuntime = "androidx.room:room-runtime:$roomVersion"
    const val roomCompiler = "androidx.room:room-compiler:$roomVersion"
    const val roomKtx = "androidx.room:room-ktx:$roomVersion"

    //Coroutines
    const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${
        coroutinesVersion
    }"

    //Test
    const val junit = "junit:junit:$junitVersion"

    const val archCoreTesting = "androidx.arch.core:core-testing:${
        archCoreTestingVersion
    }"
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${
        coroutinesVersion
    }"
    const val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${
        coroutinesVersion
    }"
    const val mockk = "io.mockk:mockk:$mockkVersion"
    const val junitKtx = "androidx.test.ext:junit-ktx:$junitKtxVersion"
    const val testCoreKtx = "androidx.test:core-ktx:$testCoreKtxVersion"
    const val robolectric = "org.robolectric:robolectric:${
        robolectricVersion
    }"
    const val hamcrest = "org.hamcrest:hamcrest-all:$hamcrestVersion"
    const val androidTestCore = "androidx.test:core:$androidTestCoreVersion"
    const val mockkAndroid = "io.mockk:mockk-android:$mockkVersion"
    const val androidCoreTesting = "androidx.arch.core:core-testing:${
        androidTestCoreVersion
    }"
    const val coroutinesAndroidTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${
        coroutinesVersion
    }"
    const val androidJunit = "androidx.test.ext:junit:$junitKtxVersion"
    const val androidTestRunner = "androidx.test:runner:${
        androidTestRunnerVersion
    }"
    const val androidTestRules = "androidx.test:rules:${
        androidTestRulesVersion
    }"
    const val androidNavigationTesting = "androidx.navigation:navigation-testing:${
        androidNavigationTestingVersion
    }"

    //Espresso
    const val espressoCore = "androidx.test.espresso:espresso-core:${
        espressoCoreVersion
    }"
    const val espressoContrib = "androidx.test.espresso:espresso-contrib:${
        espressoCoreVersion
    }"
    const val espressoIntents = "androidx.test.espresso:espresso-intents:${
        espressoCoreVersion
    }"
    const val espressoAccessibility = "androidx.test.espresso:espresso-accessibility:${
        espressoCoreVersion
    }"
    const val espressoWeb = "androidx.test.espresso:espresso-web:${
        espressoCoreVersion
    }"
    const val espressoIdling = "androidx.test.espresso.idling:idling-concurrent:${
        espressoCoreVersion
    }"
    const val debugFragmentTesting = "androidx.fragment:fragment-testing:${
        fragmentTestingVersion
    }"
    const val espressoIdlingResource = "androidx.test.espresso:espresso-idling-resource:${
        espressoCoreVersion
    }"
}

object Versions {
    const val kotlinVersion = "1.6.21"
    const val gradleVersion = "7.2.1"
    const val hiltVersion = "2.43.2"
    const val coroutinesVersion = "1.6.1"
    const val safeArgsVersion = "2.4.2"
    const val lifecycleVersion = "2.4.1"
    const val roomVersion = "2.4.2"
    const val androidxCoreVersion = "1.8.0"
    const val lifecycleViewModelKtxVersion = "2.5.0"
    const val androidxAppCompatVersion = "1.5.0"
    const val androidMaterialVersion = "1.6.1"
    const val constraintLayoutVersion = "2.1.4"
    const val navigationFragmentVersion = "2.5.0"
    const val navigationUiVersion = "2.5.0"

    //Test
    const val junitVersion = "4.13.2"
    const val archCoreTestingVersion = "2.1.0"
    const val androidCoreTestingVersion = "2.1.0"
    const val mockkVersion = "1.12.3"
    const val junitKtxVersion = "1.1.3"
    const val testCoreKtxVersion = "1.4.0"
    const val robolectricVersion = "4.7.3"
    const val hamcrestVersion = "1.3"
    const val androidTestCoreVersion = "1.4.0"
    const val androidTestRunnerVersion = "1.4.0"
    const val androidTestRulesVersion = "1.4.0"
    const val androidNavigationTestingVersion = "2.4.2"
    const val espressoCoreVersion = "3.4.0"
    const val fragmentTestingVersion = "1.4.1"

}

object AppConfig {
    const val testRunner = "com.example_app.mvpapp_kotlin.utilities.HiltCustomTestRunner"
    const val compileSdkVersion = 32
    const val minSdkVersion = 24
    const val targetSdkVersion = 32
    const val versionCode = 1
    const val buildToolsVersion = "30.0.3"
    const val versionName = "1.0"
    val sourceCompatibilityVersion = JavaVersion.VERSION_1_8
    val targetCompatibilityVersion = JavaVersion.VERSION_1_8
    const val jvmTargetVersion = "1.8"
}