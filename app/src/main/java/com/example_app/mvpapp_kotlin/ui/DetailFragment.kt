package com.example_app.mvpapp_kotlin.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example_app.mvpapp_kotlin.databinding.FragmentDetailBinding
import com.example_app.mvpapp_kotlin.utils.showSnackBar
import com.example_app.mvpapp_kotlin.viewmodel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private val detailViewModel: DetailViewModel by viewModels()
    private var noteId: Int = 0
    private lateinit var binding: FragmentDetailBinding

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

        detailViewModel.getNoteById(noteId).observe(viewLifecycleOwner) { note ->
            binding.editTextView.setText(note?.text)
        }

        binding.floatAddButton.setOnClickListener {
            showSnackBarOrAddNote(getNoteData(), binding.root)
        }
    }

    private fun showSnackBarOrAddNote(noteText: String, view: View) {
        if (detailViewModel.checkingWhiteSpaces(binding.editTextView.length()))
            showSnackBar(view)
        else
            addNote(noteText)
    }

    private fun addNote(noteText: String) {
        detailViewModel.insertOrUpdate(noteId, noteText)
        findNavController().navigate(DetailFragmentDirections.actionDetailFragmentToHomeFragment())
    }

    private fun getNoteData() = binding.editTextView.text.toString()
}
