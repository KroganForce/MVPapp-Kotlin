package com.exampleapp.mvpapp_kotlin.adapter

import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.exampleapp.mvpapp_kotlin.databinding.RecyclerViewBinding
import com.exampleapp.mvpapp_kotlin.entity.Note

class NoteHolder(
    private val binding: RecyclerViewBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(listener: NoteAdapter.NoteClickListener, item: Note) {
        // TODO: 23.06.2021 Why is there local variable here? BINDING
        val textView: TextView = binding.noteTextView
        textView.text = item.text
        textView.setOnClickListener { listener.clickOnNote(item.id) }

        // TODO: 23.06.2021 Why is there local variable here? BINDING
        val imageView: ImageView = binding.deleteButton
        imageView.setOnClickListener { listener.deleteItem(item.id) }
    }
}
