package com.example.myapplication.ui.main

import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentMainBinding
import com.example.myapplication.ui.base.BaseFragment

class MainFragment : BaseFragment(R.layout.fragment_main) {
    private val viewBinding by viewBinding(FragmentMainBinding::bind)

    private val viewModel: MainFragmentViewModel by viewModels()
}