package com.example_app.mvpapp_kotlin.utils

import android.view.View
import com.example_app.mvpapp_kotlin.R
import com.google.android.material.snackbar.Snackbar

fun showSnackBar(view: View) {
    Snackbar.make(view, R.string.empty_note, Snackbar.LENGTH_SHORT).show()
}
