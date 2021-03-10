package com.exampleapp.mvpapp_kotlin.utils

import androidx.recyclerview.widget.DiffUtil
import com.exampleapp.mvpapp_kotlin.entity.Note

class DiffUtility(private val oldList: List<Note>, private val newList: List<Note>) :
    DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldNote = oldList[oldItemPosition]
        val newNote = newList[newItemPosition]
        return oldNote.text == newNote.text
    }
}