package com.shae.notes

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * NoteEditorActivity — create or edit a single note.
 *
 * SHAE design system: paper background, ink text, hairline-ruled form
 * fields with 0px radius and 1px borders, oxide (#B3341B) as the single
 * signal colour for the save action. No glass, pills, or shadows.
 */
class NoteEditorActivity : AppCompatActivity() {

    private lateinit var dao: NoteDao
    private val scope = CoroutineScope(Job() + Dispatchers.Main)
    private var editingId: Long = -1L

    private lateinit var titleEdit: EditText
    private lateinit var contentEdit: EditText
    private lateinit var saveButton: Button
    private lateinit var deleteButton: Button
    private lateinit var errorText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.note_form)

        val db = NoteDatabase.getInstance(applicationContext)
        dao = db.noteDao()

        titleEdit = findViewById(R.id.edit_title)
        contentEdit = findViewById(R.id.edit_content)
        saveButton = findViewById(R.id.button_save)
        deleteButton = findViewById(R.id.button_delete)
        errorText = findViewById(R.id.text_error)

        editingId = intent.getLongExtra("note_id", -1L)
        if (editingId != -1L) {
            scope.launch {
                val note = dao.getById(editingId)
                if (note != null) {
                    titleEdit.setText(note.title)
                    contentEdit.setText(note.content)
                }
            }
            deleteButton.visibility = View.VISIBLE
        } else {
            deleteButton.visibility = View.GONE
        }

        saveButton.setOnClickListener { save() }
        deleteButton.setOnClickListener { delete() }
        findViewById<View>(R.id.button_cancel).setOnClickListener { finish() }
    }

    private fun save() {
        val title = titleEdit.text.toString().trim()
        val content = contentEdit.text.toString().trim()
        if (TextUtils.isEmpty(title)) {
            errorText.text = "Title is required."
            errorText.visibility = View.VISIBLE
            return
        }
        errorText.visibility = View.GONE

        val note = Note(
            id = if (editingId == -1L) 0L else editingId,
            title = title,
            content = content,
            updatedAt = System.currentTimeMillis()
        )

        scope.launch {
            dao.insert(note)
            setResult(Activity.RESULT_OK)
            finish()
        }
    }

    private fun delete() {
        if (editingId == -1L) return
        scope.launch {
            dao.getById(editingId)?.let { dao.delete(it) }
            setResult(Activity.RESULT_OK)
            finish()
        }
    }
}
