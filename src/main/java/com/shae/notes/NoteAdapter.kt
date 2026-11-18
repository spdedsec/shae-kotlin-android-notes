package com.shae.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.DateFormat
import java.util.Date

/**
 * RecyclerView adapter that renders the note list as hairline-ruled,
 * left-aligned rows following the SHAE design system (paper background,
 * ink text, oxide signal for empty state). No shadows or pills.
 */
class NoteAdapter(
    private var notes: List<Note> = emptyList(),
    private val onClick: (Note) -> Unit,
    private val onDelete: (Note) -> Unit
) : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.note_title)
        val content: TextView = itemView.findViewById(R.id.note_content)
        val meta: TextView = itemView.findViewById(R.id.note_meta)
    }

    fun submitList(newNotes: List<Note>) {
        notes = newNotes
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_note, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = notes[position]
        holder.title.text = note.title
        holder.content.text = note.content
        holder.meta.text = DateFormat.getDateTimeInstance()
            .format(Date(note.updatedAt))

        holder.itemView.setOnClickListener { onClick(note) }
        holder.itemView.findViewById<TextView>(R.id.note_delete)
            .setOnClickListener { onDelete(note) }
    }

    override fun getItemCount(): Int = notes.size
}
