package com.exampleapp.mvpapp_kotlin.ui

import android.os.Bundle
import androidx.navigation.findNavController
import com.exampleapp.mvpapp_kotlin.R
import com.exampleapp.mvpapp_kotlin.databinding.ActivityMainBinding
import com.exampleapp.mvpapp_kotlin.ui.listener.FloatingButtonClickListener
import com.exampleapp.mvpapp_kotlin.ui.listener.InitFragment
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity(), FloatingButtonClickListener, InitFragment {

    private val navController by lazy {
        findNavController(R.id.nav_host)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityMainBinding.inflate(layoutInflater).root)
    }

    override fun addNoteButton() {
        navController.navigate(DetailFragmentDirections.actionDetailFragmentToHomeFragment())
    }

    override fun showDetailFragment(id: Int) {
        navController.navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(id))
    }
}
