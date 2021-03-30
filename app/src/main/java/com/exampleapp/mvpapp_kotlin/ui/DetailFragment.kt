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
import com.exampleapp.mvpapp_kotlin.databinding.FragmentDetailBinding
import com.exampleapp.mvpapp_kotlin.presenter.DetailPresenter
import com.exampleapp.mvpapp_kotlin.utils.Const.EMPTY_NOTE
import javax.inject.Inject

class DetailFragment : BaseFragment(), DetailContract {

    @Inject
    lateinit var detailPresenter: DetailPresenter
    private var noteId: Int = 0
    private lateinit var listener: FloatingButtonClickListener
    private lateinit var editText: EditText
    private var binding: FragmentDetailBinding? = null

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
        detailPresenter.attachView(this)
        editText = binding?.editTextView!!

        binding?.let {
            it.floatAddButton.setOnClickListener {
                if (noteId == EMPTY_NOTE.value.toInt())
                    detailPresenter.add()
                else
                    detailPresenter.update(noteId)

                listener.buttonClick()
            }
        }
        detailPresenter.getNoteData()
        detailPresenter.liveData.observe(viewLifecycleOwner) { noteText ->
            editText.setText(noteText)
        }

        return view
    }

    override fun getNoteData() = editText.text.toString()

    override fun getNoteId() = noteId

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
        detailPresenter.detachView()
    }
}
