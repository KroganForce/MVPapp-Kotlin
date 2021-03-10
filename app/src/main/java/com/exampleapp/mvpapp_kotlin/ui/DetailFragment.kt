package com.exampleapp.mvpapp_kotlin.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.exampleapp.mvpapp_kotlin.utils.Const.NOTE_ID
import com.exampleapp.mvpapp_kotlin.R
import com.exampleapp.mvpapp_kotlin.contract.DetailContract
import com.exampleapp.mvpapp_kotlin.presenter.DetailPresenter
import com.exampleapp.mvpapp_kotlin.utils.toEditable
import com.google.android.material.floatingactionbutton.FloatingActionButton
import javax.inject.Inject

class DetailFragment : BaseFragment(), DetailContract {

    @Inject
    lateinit var detailPresenter: DetailPresenter
    private var noteId: Int = 0
    private lateinit var listener: FloatingButtonClickListener
    private lateinit var editText: EditText

    interface FloatingButtonClickListener {
        fun buttonClick()
    }

    fun newInstance(id: Int): DetailFragment {
        val detailFragment = DetailFragment()
        Bundle().apply {
            putInt(NOTE_ID.name, id)
            detailFragment.arguments = this
        }
        return detailFragment
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FloatingButtonClickListener)
            listener = context
        else
            throw RuntimeException(context.toString())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Bundle().apply {
            noteId = this.getInt(NOTE_ID.name)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail, container, false)

        val floatingActionButton: FloatingActionButton = view.findViewById(R.id.float_add_button)
        floatingActionButton.setOnClickListener {
            if (noteId == -1)
                detailPresenter.add()
            else
                detailPresenter.update(noteId)

            listener.buttonClick()
        }
        detailPresenter.attachView(this)
        editText = view.findViewById(R.id.edit_text_view)
        editText.text = detailPresenter.getNoteData().toEditable()
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        detailPresenter.detachView()
    }

    override fun getNoteData() = editText.text.toString()

    override fun getNoteId() = noteId
}