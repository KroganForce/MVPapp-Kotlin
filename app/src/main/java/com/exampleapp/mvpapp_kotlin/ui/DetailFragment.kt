package com.exampleapp.mvpapp_kotlin.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.navigation.fragment.navArgs
import com.exampleapp.mvpapp_kotlin.R
import com.exampleapp.mvpapp_kotlin.contract.DetailContract
import com.exampleapp.mvpapp_kotlin.presenter.DetailPresenter
import com.exampleapp.mvpapp_kotlin.utils.Const
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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FloatingButtonClickListener)
            listener = context
        else
            throw RuntimeException(context.toString())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args: DetailFragmentArgs by navArgs()
        noteId = args.id
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail, container, false)
        detailPresenter.attachView(this)

        val floatingActionButton: FloatingActionButton = view.findViewById(R.id.float_add_button)
        floatingActionButton.setOnClickListener {
            if (noteId == Const.EMPTY_NOTE.value.toInt())
                detailPresenter.add()
            else
                detailPresenter.update(noteId)

            listener.buttonClick()
        }
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
