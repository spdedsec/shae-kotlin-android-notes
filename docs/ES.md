# shae-kotlin-android-notes (Español)

Aplicación de notas para Android en Kotlin, basada en el SHAE Field
Manual design system: papel cálido #F2EEE5, tinta #191712, una señal
óxido #B3341B por vista, 0px de radio, bordes de 1px, tarjetas definidas
por borde, contraste WCAG AA, fallback de movimiento reducido.

## Funcionalidades

- Crear una nota (título, contenido)
- Listar todas las notas (RecyclerView, filas con reglas finas)
- Ver / editar una nota
- Eliminar una nota
- Persistencia con base de datos Room (SharedPreferences como fallback posible)

## Instalación

Requiere el Android SDK (no disponible localmente). No se puede
compilar-probar localmente.

```bash
export ANDROID_SDK_ROOT=/ruta/al/android-sdk
$ANDROID_SDK_ROOT/cmdline-tools/latest/bin/sdkmanager --licenses

git clone https://github.com/spdedsec/shae-kotlin-android-notes.git
cd shae-kotlin-android-notes

./scripts/install.sh          # APK de depuración
./scripts/install.sh release# APK de release (firmar antes de publicar)
```

## Uso

Abrir la app, tocar el botón de acción flotante (óxido #B3341B, única
señal) para crear una nota. Tocar una fila para ver/editar; el icono de
eliminar en una fila la borra. Las notas se guardan en la base de datos
Room `shae_notes.db`.

## Sistema de diseño

Véase `DESIGN.md`. La UI usa la paleta SHAE.

## Licencia

MIT. Véase `LICENSE`.
