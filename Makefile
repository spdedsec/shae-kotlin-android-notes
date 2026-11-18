# Makefile — shae-kotlin-android-notes
#
# Targets: build test run install clean release help
#
# SHAE design system applied: paper #F2EEE5, ink #191712, oxide #B3341B,
# 0px radius, 1px borders, border-defined cards.
#
# NOTE: Android SDK + Gradle are required and NOT available locally.

.PHONY: build test run install clean release help

SDK_ROOT ?= $(ANDROID_SDK_ROOT)
GRADLE   ?= $(shell command -v gradle 2>/dev/null || echo ./gradlew)
APP_APK_DEBUG  := app/build/outputs/apk/debug/app-debug.apk
APP_APK_RELEASE:= app/build/outputs/apk/release/app-release-unsigned.apk
VERSION := v0.1.0
PKG_NAME := shae-kotlin-android-notes

build:
	@echo "==> Building $(PKG_NAME) (debug)"
	$(GRADLE) assembleDebug
	@echo "APK: $(APP_APK_DEBUG)"

test: build
	@echo "==> Running unit/instrumented tests"
	$(GRADLE) testDebugUnitTest

run: build
	@echo "==> Install debug APK to connected device/emulator"
	adb install -r $(APP_APK_DEBUG)

install:
	@echo "==> Install requirements"
	@echo "1. Install Android SDK; export ANDROID_SDK_ROOT"
	@echo "2. sdkmanager --licenses"
	@echo "3. make build"

clean:
	$(GRADLE) clean
	rm -rf build dist

release: clean
	@echo "==> Building release $(VERSION)"
	$(GRADLE) assembleRelease
	@echo "APK: $(APP_APK_RELEASE)  (sign with your release keystore before publishing)"
	@echo "Create the GitHub release: gh release create $(VERSION) $(APP_APK_RELEASE) --title '$(PKG_NAME) $(VERSION)'"

help:
	@echo "Usage: make [target]"
	@echo ""
	@echo "Targets:"
	@echo "  build    debug APK via Gradle"
	@echo "  test     run unit tests"
	@echo "  run      install debug APK to device (adb)"
	@echo "  install  print install instructions"
	@echo "  clean    clean build output"
	@echo "  release  build release APK"
	@echo "  help     show this help"
