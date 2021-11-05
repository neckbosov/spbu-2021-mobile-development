package com.example.myapplication.ui.likes

import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentLikesBinding
import com.example.myapplication.ui.base.BaseFragment
import com.example.myapplication.ui.profile.ProfileViewModel

class LikesFragment : BaseFragment(R.layout.fragment_profile) {
    private val viewBinding by viewBinding(FragmentLikesBinding::bind)

    private val viewModel: ProfileViewModel by viewModels()
}