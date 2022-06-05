package com.example_app.mvpapp_kotlin.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example_app.mvpapp_kotlin.adapter.listener.NoteClickListener
import com.example_app.mvpapp_kotlin.databinding.RecyclerNoteItemBinding
import com.example_app.mvpapp_kotlin.entity.Note

class NoteHolder(
    private val binding: RecyclerNoteItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(listener: NoteClickListener, item: Note) {
        binding.noteTextView.apply {
            text = item.text
            setOnClickListener {
                listener.clickOnNote(item.id)
            }
        }
        binding.deleteButton.apply {
            setOnClickListener {
                listener.deleteItem(item.id)
            }
        }
    }
}
