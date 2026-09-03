# BitChord 1.5 — 32-bit Android compatibility build

This source tree is patched for Android devices running a 32-bit Android userspace,
such as the Redmi 9A Sport configuration discussed with the user.

## Changes

- `app/build.gradle.kts`: ABI changed from `arm64-v8a, x86_64` to `armeabi-v7a`.
- Added `NativeSupport.kt` to detect 32-bit Android at runtime.
- `BeatTracker` and `VocalTracker` return `null` on 32-bit before ONNX Runtime is touched.
  This prevents a missing 32-bit ONNX native library from crashing the app.
- The native BitChord DSP analyzer remains enabled and is compiled as `armeabi-v7a`.

## Expected behavior on 32-bit Android

Core playback/UI plus the native DSP analysis path can run. The Beat This! and
vocal-separation ONNX features are intentionally unavailable on 32-bit.

## Build

Open the project in Android Studio with a compatible JDK/Android SDK/NDK installed,
then build the production release variant:

`./gradlew :app:assembleProdRelease`

The expected APK is under:

`app/build/outputs/apk/prod/release/`

The app is unsigned unless the original release keystore configured by the project
owner is available. An APK signed with a different key cannot update an existing
installation signed with the original key; uninstalling the old app is required in
that case.

## SWV GitHub Actions build

This fork includes `.github/workflows/build-swv.yml`. Push the project to a GitHub repository and run **Actions → Build SWV 32-bit APK → Run workflow**. The workflow builds the `prodRelease` variant and uploads `SWV-1.5-32bit.apk` as a workflow artifact.

The workflow uses JDK 17 and installs Android API 36, Build Tools 36.0.0, CMake 3.22.1, and NDK 27.0.12077973. Gradle's official `setup-gradle` action handles the Gradle environment and dependency caching.
