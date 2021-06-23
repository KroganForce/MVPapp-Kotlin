package com.exampleapp.mvpapp_kotlin.utils

import android.view.View
import com.exampleapp.mvpapp_kotlin.R
import com.google.android.material.snackbar.Snackbar

fun showSnackBar(view: View) {
    Snackbar.make(view, R.string.empty_note, Snackbar.LENGTH_SHORT).show()
}
