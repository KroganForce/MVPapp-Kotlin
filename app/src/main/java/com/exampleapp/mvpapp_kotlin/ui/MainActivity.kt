package com.exampleapp.mvpapp_kotlin.ui

import android.os.Bundle
import androidx.navigation.NavController
import com.exampleapp.mvpapp_kotlin.R
import com.exampleapp.mvpapp_kotlin.utils.initNavController

class MainActivity : BaseActivity(), DetailFragment.FloatingButtonClickListener,
    HomeFragment.InitFragment {

    private val fragmentManager = supportFragmentManager
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = initNavController(fragmentManager)

        showHomeFragment()
    }

    private fun showHomeFragment() {
        navController.navigate(R.id.homeFragment2)
    }

    override fun buttonClick() {
        fragmentManager.popBackStack()
    }

    override fun showDetailFragment(id: Int) {
        navController.navigate(R.id.detailFragment2, DetailFragment().newInstance(id))
    }
}