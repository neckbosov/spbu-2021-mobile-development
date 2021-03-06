package com.example.myapplication.ui.profile

import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentProfileBinding
import com.example.myapplication.ui.base.BaseFragment

class ProfileFragment : BaseFragment(R.layout.fragment_likes) {
    private val viewBinding by viewBinding(FragmentProfileBinding::bind)

    private val viewModel: ProfileViewModel by viewModels()
}