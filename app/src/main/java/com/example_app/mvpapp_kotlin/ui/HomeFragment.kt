package com.example_app.mvpapp_kotlin.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example_app.mvpapp_kotlin.adapter.NoteAdapter
import com.example_app.mvpapp_kotlin.adapter.listener.NoteClickListener
import com.example_app.mvpapp_kotlin.databinding.FragmentHomeBinding
import com.example_app.mvpapp_kotlin.utils.EMPTY_ID
import com.example_app.mvpapp_kotlin.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), NoteClickListener {

    private val homeViewModel: HomeViewModel by viewModels()
    private val notesAdapter: NoteAdapter = NoteAdapter(this)
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentHomeBinding.inflate(inflater, container, false).also {
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        setData()
        binding.floatActionButton.setOnClickListener {
            floatButtonPush()
        }
    }

    private fun initRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = notesAdapter
        }
    }

    private fun setData() {
        homeViewModel.allNotes.observe(viewLifecycleOwner) { list ->
            notesAdapter.updateList(list)
        }
    }

    private fun floatButtonPush() {
        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(
            EMPTY_ID))
    }

   override fun deleteItem(id: Int) {
        homeViewModel.deleteNote(id)
    }

    override fun clickOnNote(id: Int) {
        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(id))
    }
}
