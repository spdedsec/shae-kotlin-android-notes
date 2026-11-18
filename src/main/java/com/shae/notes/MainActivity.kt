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
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * MainActivity — note list screen.
 *
 * Layout follows SHAE: paper background (#F2EEE5), ink text (#191712),
 * hairline-ruled bordered rows, 0px radius, oxide (#B3341B) single signal
 * for the floating action button. No elevation/shadows, no glass, no pills.
 */
class MainActivity : AppCompatActivity() {

    private lateinit var adapter: NoteAdapter
    private lateinit var dao: NoteDao
    private val scope = CoroutineScope(Job() + Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = NoteDatabase.getInstance(applicationContext)
        dao = db.noteDao()

        val recyclerView: RecyclerView = findViewById(R.id.notes_list)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = NoteAdapter(
            onClick = { note -> openNote(note) },
            onDelete = { note -> deleteNote(note) }
        )
        recyclerView.adapter = adapter

        val fab: FloatingActionButton = findViewById(R.id.fab_add)
        fab.setOnClickListener { openNote(null) }

        loadNotes()
    }

    private fun loadNotes() {
        scope.launch {
            val notes = dao.getAll()
            adapter.submitList(notes)
            val empty: TextView = findViewById(R.id.empty_state)
            empty.visibility = if (notes.isEmpty()) View.VISIBLE else View.GONE
        }
    }

    private fun openNote(note: Note?) {
        val intent = Intent(this, NoteEditorActivity::class.java)
        intent.putExtra("note_id", note?.id ?: -1L)
        startActivityForResult(intent, 1001)
    }

    private fun deleteNote(note: Note) {
        scope.launch {
            dao.delete(note)
            loadNotes()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1001 && resultCode == Activity.RESULT_OK) {
            loadNotes()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        NoteDatabase.getInstance(applicationContext).clearAllTables() // no-op guard
    }
}
