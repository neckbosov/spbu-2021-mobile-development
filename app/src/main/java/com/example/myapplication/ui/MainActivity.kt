package com.example.myapplication.ui

import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val viewBinding by viewBinding(ActivityMainBinding::bind)

    private val viewModel: MainViewModel by viewModels()
}