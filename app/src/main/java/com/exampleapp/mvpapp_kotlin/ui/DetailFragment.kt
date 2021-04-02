package com.exampleapp.mvpapp_kotlin.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.navigation.fragment.navArgs
import com.exampleapp.mvpapp_kotlin.R
import com.exampleapp.mvpapp_kotlin.databinding.FragmentDetailBinding
import com.exampleapp.mvpapp_kotlin.presenter.DetailPresenter
import com.exampleapp.mvpapp_kotlin.utils.EMPTY_NOTE
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class DetailFragment : DaggerFragment() {

    @Inject
    lateinit var detailPresenter: DetailPresenter
    private var noteId: Int = 0
    private lateinit var listener: FloatingButtonClickListener
    private lateinit var editText: EditText
    private lateinit var binding: FragmentDetailBinding

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
        binding = FragmentDetailBinding.bind(view)
        editText = binding.editTextView

        detailPresenter.getNoteById(noteId).observe(viewLifecycleOwner) { noteText ->
            editText.setText(noteText)
        }

        binding.floatAddButton.setOnClickListener {
            if (noteId == EMPTY_NOTE)
                detailPresenter.add(getNoteData())
            else
                detailPresenter.update(noteId, getNoteData())

            listener.buttonClick()
        }

        return view
    }

    private fun getNoteData() = editText.text.toString()

    private fun checkEmptyNote(noteText: String): Boolean {
        return noteText.contains(Regex("""\s""")) || editText.length() == 0 || noteText.contains(
            Regex("""\s + \S""")
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        detailPresenter.detachView()
    }
}
