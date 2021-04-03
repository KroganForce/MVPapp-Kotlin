package com.exampleapp.mvpapp_kotlin.utils

import android.view.View
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.exampleapp.mvpapp_kotlin.R
import com.google.android.material.snackbar.Snackbar

fun showSnackBar(view: View) {
    Snackbar.make(view, R.string.empty_note, Snackbar.LENGTH_SHORT).show()
}

fun initNavController(manager: FragmentManager): NavController {
    val navHostFragment = manager.findFragmentById(R.id.nav_host) as NavHostFragment
    return navHostFragment.navController
}