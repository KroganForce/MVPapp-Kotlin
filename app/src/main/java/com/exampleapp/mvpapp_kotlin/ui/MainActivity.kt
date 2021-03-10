package com.exampleapp.mvpapp_kotlin.ui

import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import com.exampleapp.mvpapp_kotlin.utils.Const
import com.exampleapp.mvpapp_kotlin.R

class MainActivity : BaseActivity(), DetailFragment.FloatingButtonClickListener,
    HomeFragment.InitFragment {

    private val fragmentManager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showHomeFragment()
    }

    private fun showHomeFragment() {
        var fragment = fragmentManager.findFragmentById(R.id.fragment_container)
        if (fragment == null) {
            fragment = HomeFragment().newInstance()
            fragmentManager.beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .add(R.id.fragment_container, fragment)
                .commit()
        }
    }

    override fun buttonClick() {
        fragmentManager.popBackStack()
    }

    override fun showDetailFragment(id: Int) {
        var fragment = fragmentManager.findFragmentById(R.id.fragment_container)
        if (fragment == null) {
            fragment = DetailFragment().newInstance(id)
            fragmentManager.beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(Const.DETAIL_FRAGMENT.name)
                .commit()
        }
    }
}