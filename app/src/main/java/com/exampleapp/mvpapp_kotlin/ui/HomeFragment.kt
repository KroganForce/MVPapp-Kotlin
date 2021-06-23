package com.exampleapp.mvpapp_kotlin.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.exampleapp.mvpapp_kotlin.R
import com.exampleapp.mvpapp_kotlin.adapter.NoteAdapter
import com.exampleapp.mvpapp_kotlin.databinding.FragmentHomeBinding
import com.exampleapp.mvpapp_kotlin.presenter.HomePresenter
import com.exampleapp.mvpapp_kotlin.utils.EMPTY_ID
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class HomeFragment : DaggerFragment(), NoteAdapter.NoteClickListener {

    @Inject
    lateinit var homePresenter: HomePresenter
    private lateinit var initFragmentListener: InitFragment
    private lateinit var notesAdapter: NoteAdapter
    private lateinit var binding: FragmentHomeBinding

    // TODO: 23.06.2021 move interface into separate package (optionally)
    interface InitFragment {
        fun showDetailFragment(id: Int)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is InitFragment)
            initFragmentListener = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // TODO: 23.06.2021 run ext
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // TODO: 23.06.2021 move to onViewCreated
        binding = FragmentHomeBinding.bind(view)
        binding.floatActionButton.setOnClickListener { floatButtonPush() }
        setData()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        // TODO: 23.06.2021 init into recyclerView apply block
        val linearLayoutManager = LinearLayoutManager(this.context).apply {
            orientation = RecyclerView.VERTICAL
        }

        val recyclerView = binding.recyclerView.apply {
            layoutManager = linearLayoutManager
        }

        // TODO: 23.06.2021 init into recyclerView apply block
        notesAdapter = NoteAdapter(listener = this)
        recyclerView.adapter = notesAdapter
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
