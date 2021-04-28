package com.exampleapp.mvpapp_kotlin.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.exampleapp.mvpapp_kotlin.databinding.FragmentDetailBinding
import com.exampleapp.mvpapp_kotlin.presenter.DetailPresenter
import com.exampleapp.mvpapp_kotlin.ui.listener.FloatingButtonClickListener
import com.exampleapp.mvpapp_kotlin.utils.showSnackBar
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class DetailFragment : DaggerFragment() {

    @Inject
    lateinit var detailPresenter: DetailPresenter
    private var noteId: Int = 0
    private lateinit var listener: FloatingButtonClickListener
    private lateinit var binding: FragmentDetailBinding

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
    ): View {
        return FragmentDetailBinding.inflate(inflater, container, false).also {
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        detailPresenter.getNoteById(noteId).observe(viewLifecycleOwner) { noteText ->
            binding.editTextView.setText(noteText)
        }

        binding.floatAddButton.setOnClickListener {
            showSnackBarOrAddNote(getNoteData(), binding.root)
        }
    }

    private fun showSnackBarOrAddNote(noteText: String, view: View) {
        if (detailPresenter.checkingWhiteSpaces(binding.editTextView.length()))
            showSnackBar(view)
        else
            addNote(noteText)
    }

    private fun addNote(noteText: String) {
        detailPresenter.insertOrUpdate(noteId, noteText)
        listener.addNoteButton()
    }

    private fun getNoteData() = binding.editTextView.text.toString()

    override fun onDestroyView() {
        super.onDestroyView()
        detailPresenter.detachView()
    }
}
