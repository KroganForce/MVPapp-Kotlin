package com.exampleapp.mvpapp_kotlin.ui

import android.os.Bundle
import androidx.navigation.NavController
import com.exampleapp.mvpapp_kotlin.R
import com.exampleapp.mvpapp_kotlin.utils.initNavController
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity(), DetailFragment.FloatingButtonClickListener,
    HomeFragment.InitFragment {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = initNavController(supportFragmentManager)

    }

    override fun buttonClick() {
        navController.navigate(DetailFragmentDirections.actionDetailFragmentToHomeFragment())
    }

    override fun showDetailFragment(id: Int) {
        navController.navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(id))
    }
}
