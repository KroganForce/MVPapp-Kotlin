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
import com.exampleapp.mvpapp_kotlin.contract.HomeContract
import com.exampleapp.mvpapp_kotlin.databinding.FragmentHomeBinding
import com.exampleapp.mvpapp_kotlin.entity.Note
import com.exampleapp.mvpapp_kotlin.presenter.HomePresenter
import com.exampleapp.mvpapp_kotlin.utils.Const
import javax.inject.Inject

class HomeFragment : BaseFragment(), NoteAdapter.NoteClickListener, HomeContract {

    @Inject
    lateinit var homePresenter: HomePresenter
    private lateinit var initFragmentListener: InitFragment
    private lateinit var notesAdapter: NoteAdapter
    private var binding: FragmentHomeBinding? = null

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
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        binding = FragmentHomeBinding.bind(view)
        binding?.let { it.floatActionButton.setOnClickListener { floatButtonPush() } }

        initPresenter()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()

    }

    private fun initRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(this.context).apply {
            orientation = RecyclerView.VERTICAL
        }
        val recyclerView = binding?.recyclerView
        recyclerView?.apply {
            layoutManager = linearLayoutManager
        }
        notesAdapter = NoteAdapter(listener = this)
        recyclerView?.adapter = notesAdapter
    }

    override fun setData(list: List<Note>) {
        notesAdapter.updateList(list)
    }

    private fun initPresenter() {
        homePresenter.attachView(view = this)
        homePresenter.viewIsReady(owner = this)
    }

    private fun floatButtonPush() {
        initFragmentListener.showDetailFragment(Const.EMPTY_NOTE.value.toInt())
    }

    override fun clickOnNote(id: Int) {
        initFragmentListener.showDetailFragment(id)
    }

    override fun deleteItem(id: Int) {
        homePresenter.deleteNote(id)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
        homePresenter.detachView()
    }
}
