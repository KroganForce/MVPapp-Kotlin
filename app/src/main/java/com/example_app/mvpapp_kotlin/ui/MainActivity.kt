package com.example_app.mvpapp_kotlin.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example_app.mvpapp_kotlin.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityMainBinding.inflate(layoutInflater).root)
    }
}
