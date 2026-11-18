#!/usr/bin/env bash
#
# scripts/install.sh — shae-kotlin-android-notes
#
# Builds and installs the Android notes app.
#
# Requires: Android SDK (not available locally). Cannot compile-test locally.
# Set ANDROID_SDK_ROOT and accept licences before building.
#
# Usage:
#   ./scripts/install.sh        # debug build
#   ./scripts/install.sh release# release build
set -euo pipefail

REPO_ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
cd "$REPO_ROOT"

require_sdk() {
    if [ -z "${ANDROID_SDK_ROOT:-}" ]; then
        echo "Error: ANDROID_SDK_ROOT is not set." >&2
        echo "Install the Android SDK and export ANDROID_SDK_ROOT, then re-run." >&2
        exit 1
    fi
    if [ ! -x "$ANDROID_SDK_ROOT/cmdline-tools/latest/bin/sdkmanager" ] && \
       [ ! -x "$ANDROID_SDK_ROOT/tools/bin/sdkmanager" ]; then
        echo "Error: Android SDK command-line tools not found under \$ANDROID_SDK_ROOT." >&2
        exit 1
    fi
}

build_debug() {
    require_sdk
    if command -v gradle >/dev/null 2>&1; then
        gradle assembleDebug
    elif [ -x ./gradlew ]; then
        ./gradlew assembleDebug
    else
        echo "Error: Gradle not found. Install Gradle or use ./gradlew." >&2
        exit 1
    fi
    echo "Debug APK: app/build/outputs/apk/debug/app-debug.apk"
}

build_release() {
    require_sdk
    if command -v gradle >/dev/null 2>&1; then
        gradle assembleRelease
    elif [ -x ./gradlew ]; then
        ./gradlew assembleRelease
    else
        echo "Error: Gradle not found." >&2
        exit 1
    fi
    echo "Release APK: app/build/outputs/apk/release/app-release.apk (sign before install)"
}

case "${1:-debug}" in
    release) build_release ;;
    *) build_debug ;;
esac
