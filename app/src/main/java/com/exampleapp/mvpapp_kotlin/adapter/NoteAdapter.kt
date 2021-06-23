package com.exampleapp.mvpapp_kotlin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.exampleapp.mvpapp_kotlin.R
import com.exampleapp.mvpapp_kotlin.databinding.FragmentDetailBinding
import com.exampleapp.mvpapp_kotlin.databinding.RecyclerViewBinding
import com.exampleapp.mvpapp_kotlin.entity.Note
import com.exampleapp.mvpapp_kotlin.utils.DiffUtility

class NoteAdapter(private val listener: NoteClickListener) : RecyclerView.Adapter<NoteHolder>() {

    private val list = arrayListOf<Note>()

    // TODO: 23.06.2021 move interface into separate package (optionally)
    interface NoteClickListener {
        fun clickOnNote(id: Int)
        fun deleteItem(id: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        // TODO: 23.06.2021 run ext (optionally)
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_view, parent, false)
        return NoteHolder(RecyclerViewBinding.bind(view))
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        holder.bind(listener, list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun updateList(newList: List<Note>) {
        // TODO: 23.06.2021 apply ext for diffResult (optionally)
        val diffResult: DiffUtil.DiffResult = DiffUtil.calculateDiff(DiffUtility(list, newList))
        list.clear()
        list.addAll(newList)
        diffResult.dispatchUpdatesTo(this)
    }
}
