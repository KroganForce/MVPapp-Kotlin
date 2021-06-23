package com.exampleapp.mvpapp_kotlin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.exampleapp.mvpapp_kotlin.adapter.listener.NoteClickListener
import com.exampleapp.mvpapp_kotlin.databinding.RecyclerNoteItemBinding
import com.exampleapp.mvpapp_kotlin.entity.Note
import com.exampleapp.mvpapp_kotlin.utils.DiffUtility

class NoteAdapter(private val listener: NoteClickListener) : RecyclerView.Adapter<NoteHolder>() {

    private val list = arrayListOf<Note>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        return NoteHolder(
            RecyclerNoteItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        holder.bind(listener, list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun updateList(newList: List<Note>) {
        DiffUtil.calculateDiff(DiffUtility(list, newList)).apply {
            list.clear()
            list.addAll(newList)
            dispatchUpdatesTo(this@NoteAdapter)
        }
    }
}
