package com.exampleapp.mvpapp_kotlin.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.exampleapp.mvpapp_kotlin.adapter.NoteAdapter
import com.exampleapp.mvpapp_kotlin.adapter.listener.NoteClickListener
import com.exampleapp.mvpapp_kotlin.databinding.FragmentHomeBinding
import com.exampleapp.mvpapp_kotlin.presenter.HomePresenter
import com.exampleapp.mvpapp_kotlin.ui.listener.InitFragment
import com.exampleapp.mvpapp_kotlin.utils.EMPTY_ID
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class HomeFragment : DaggerFragment(), NoteClickListener {

    @Inject
    lateinit var homePresenter: HomePresenter
    private lateinit var initFragmentListener: InitFragment
    private val notesAdapter: NoteAdapter = NoteAdapter(this)
    private lateinit var binding: FragmentHomeBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is InitFragment)
            initFragmentListener = context
    }

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
        homePresenter.allNotes.observe(viewLifecycleOwner) { list ->
            notesAdapter.updateList(list)
        }
    }

    private fun floatButtonPush() {
        initFragmentListener.showDetailFragment(EMPTY_ID)
    }

    override fun clickOnNote(id: Int) {
        initFragmentListener.showDetailFragment(id)
    }

    override fun deleteItem(id: Int) {
        homePresenter.deleteNote(id)
    }

    override fun onDestroy() {
        super.onDestroy()
        homePresenter.detachView()
    }
}
