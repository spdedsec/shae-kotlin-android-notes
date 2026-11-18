package com.shae.notes

/**
 * shae-kotlin-android-notes
 * -------------------------
 * Android notes application written in Kotlin, built on the SHAE Field
 * Manual design system.
 *
 * Features:
 *   - create a note (title, content)
 *   - list all notes (RecyclerView)
 *   - view / edit a note
 *   - delete a note
 *   - persist with Room (fallback: SharedPreferences)
 *
 * Requires: Android SDK (not available locally). Cannot compile-test locally.
 *
 * Design system (SHAE Field Manual):
 *   Warm paper #F2EEE5, ink #191712, oxide signal #B3341B; 0px radius,
 *   1px borders, border-defined cards; Archivo / Instrument Sans /
 *   JetBrains Mono typography intent; no gradients, glass, pills, shadows.
 */
data class Note(
    val id: Long = 0L,
    val title: String,
    val content: String,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)
