# DESIGN.md — shae-kotlin-android-notes

SHAE Field Manual design system applied to the Android notes app.

## Palette

| Role | Hex | Usage |
|------|-----|-------|
| Warm paper | #F2EEE5 | Activity backgrounds, card fills, button fill |
| Ink | #191712 | Primary text, hairline borders, button text |
| Oxide signal | #B3341B | Floating action button, error text, delete icon |

Defined in `src/main/res/values/colors.xml` and applied in
`themes.xml` (`Theme.ShaeNotes`) and `Widget.Shae.*` component styles.

## Typography

- Display / headlines: Archivo
- Body: Instrument Sans
- Labels / code: JetBrains Mono (tabular numerals)

Applied through `textAppearance` theme attributes; system fallbacks used
where the SHAE typefaces are not bundled.

## Layout & form

- 0px radius — `cornerRadius="0dp"` on buttons, text fields, rows
- 1px borders — `strokeWidth="1dp"`, hairline rules between sections
- Border-defined cards — rows use `note_row_background.xml` (solid paper +
  1px ink stroke), not CardView elevation/shadow
- Left-aligned data — list rows are left-aligned; no centered heroes
- Uppercase only for short labels — button text uses sentence case

## Motion

State changes 140–240ms; any shared-element transition 320ms.
Transform/opacity/clip-path only. `prefers-reduced-motion` equivalent
(Android `windowAnimationFrameRate` / disable animations) honoured. No
animation libraries for simple UI.

## Accessibility

- WCAG AA contrast (ink on paper; oxide used only as a signal)
- Visible oxide focus indication on interactive elements
- Complete keyboard / navigation paths (DPAD, TalkBack labels)
- 44dp touch targets on all interactive elements
- No horizontal overflow

## Anti-patterns avoided

No gradients, glows, pills, glass cards, shadows, or elevation on cards.

## Files

- `src/main/java/com/shae/notes/*.kt` — Kotlin source
- `src/main/res/` — layouts, drawables, values (design tokens)
- `AndroidManifest.xml`, `build.gradle` — Android project config
- `README.md`, `DESIGN.md` — docs
- `scripts/install.sh`, `Makefile` — build/test/release
- `docs/DE.md`, `docs/ES.md` — German / Spanish
