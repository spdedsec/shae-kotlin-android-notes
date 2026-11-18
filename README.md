# shae-kotlin-android-notes

Android notes application written in Kotlin, built on the SHAE Field Manual
design system: warm paper #F2EEE5, ink #191712, one oxide-red #B3341B
signal per view, 0px radius, 1px borders, border-defined cards, WCAG AA
contrast, reduced-motion fallbacks.

## Features

- create a note (title, content)
- list all notes (RecyclerView, hairline-ruled rows)
- view / edit a note
- delete a note
- persist with Room database (SharedPreferences fallback possible)

## Install

Requires the Android SDK (not available locally). Cannot compile-test locally.

```bash
export ANDROID_SDK_ROOT=/path/to/android-sdk
$ANDROID_SDK_ROOT/cmdline-tools/latest/bin/sdkmanager --licenses

git clone https://github.com/spdedsec/shae-kotlin-android-notes.git
cd shae-kotlin-android-notes

./scripts/install.sh        # debug APK
# or
./scripts/install.sh release# release APK (sign before publishing)
```

## Usage

Open the app, tap the floating action button (oxide #B3341B, the single
signal colour) to create a note. Tap a row to view/edit; tap the delete
icon on a row to remove it. Notes persist in the Room database
`shae_notes.db`.

## Architecture

| File | Responsibility |
|------|----------------|
| `Note.kt` | data class (title, content, timestamps) |
| `NoteDao.kt` | Room data-access operations |
| `NoteDatabase.kt` | Room database instance |
| `NoteAdapter.kt` | RecyclerView list rendering |
| `MainActivity.kt` | note list screen |
| `NoteEditorActivity.kt` | create / edit form |
| `build.gradle` | Gradle build (Room, coroutines, Material) |
| `AndroidManifest.xml` | app declaration |

## Design system

See `DESIGN.md`. UI uses the SHAE palette:

- Warm paper `#F2EEE5` — backgrounds, cards
- Ink `#191712` — primary text, hairline borders
- Oxide `#B3341B` — single signal (FAB, errors, delete)

Rows use 0px radius, 1px borders, border-defined cards; no elevation,
shadows, glass, or pills.

## Build / release

```bash
make build      # debug APK
make release    # release APK (unsigned; sign with your keystore)
```

Android SDK and Gradle are required and are not available locally.

## License

MIT. See `LICENSE`.
