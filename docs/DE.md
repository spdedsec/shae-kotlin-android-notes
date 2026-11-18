# shae-kotlin-android-notes (Deutsch)

Android-Notizen-App in Kotlin, nach dem SHAE Field Manual Design System:
warmes Papier #F2EEE5, Tinte #191712, ein Oxid-Rot #B3341B als einziges
Signal pro Ansicht, 0px Radius, 1px Rahmen, Rahmen-definierte Karten,
WCAG-AA-Kontrast, reduzierte-Bewegung-Fallbacks.

## Funktionen

- Notiz erstellen (Titel, Inhalt)
- Alle Notizen auflisten (RecyclerView, haardünne Zeilen)
- Notiz ansehen / bearbeiten
- Notiz löschen
- Speicherung mit Room-Datenbank (SharedPreferences als Fallback möglich)

## Installation

Erfordert das Android SDK (nicht lokal verfügbar). Kann nicht lokal
kompiliert-getestet werden.

```bash
export ANDROID_SDK_ROOT=/pfad/zum/android-sdk
$ANDROID_SDK_ROOT/cmdline-tools/latest/bin/sdkmanager --licenses

git clone https://github.com/spdedsec/shae-kotlin-android-notes.git
cd shae-kotlin-android-notes

./scripts/install.sh          # Debug-APK
./scripts/install.sh release# Release-APK (vor Veröffentlichung signieren)
```

## Verwendung

App öffnen, Schwimmende Aktionstaste (Oxid #B3341B, einziges Signal)
tippen, um eine Notiz zu erstellen. In eine Zeile tippen zum Ansehen/
Bearbeiten; Lösch-Symbol in einer Zeile zum Entfernen. Notizen werden in
der Room-Datenbank `shae_notes.db` gespeichert.

## Design-System

Siehe `DESIGN.md`. Die UI nutzt die SHAE-Palette.

## Lizenz

MIT. Siehe `LICENSE`.
