package com.exampleapp.mvpapp_kotlin.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.exampleapp.mvpapp_kotlin.R
import com.exampleapp.mvpapp_kotlin.entity.Note

class NoteHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(listener: NoteAdapter.NoteClickListener, item: Note) {

        val textView: TextView = view.findViewById(R.id.note_text_view)
        textView.text = item.text
        textView.setOnClickListener { listener.clickOnNote(item.id) }

        val imageView: ImageView = view.findViewById(R.id.delete_button)
        imageView.setOnClickListener { listener.deleteItem(item.id) }
    }
}