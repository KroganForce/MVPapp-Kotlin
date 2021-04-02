package com.exampleapp.mvpapp_kotlin.utils

import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.exampleapp.mvpapp_kotlin.R

fun initNavController(manager: FragmentManager): NavController {
    val navHostFragment = manager.findFragmentById(R.id.nav_host) as NavHostFragment
    return navHostFragment.navController
}