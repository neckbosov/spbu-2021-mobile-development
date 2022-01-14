package com.example.myapplication.ui.profile

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentProfileBinding
import com.example.myapplication.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import dev.chrisbanes.insetter.applyInsetter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProfileFragment : BaseFragment(R.layout.fragment_profile) {
    private val viewBinding by viewBinding(FragmentProfileBinding::bind)

    private val viewModel: ProfileViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToViewState()
        viewModel.loadProfile()
        viewBinding.logoutButton.applyInsetter {
            type(statusBars = true) { margin() }
        }
        viewBinding.profileToolbar.applyInsetter {
            type(statusBars = true) { margin() }
        }
        viewBinding.logoutButton.setOnClickListener {
            viewModel.logout()
        }
    }

    private fun subscribeToViewState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.userViewState.collect { viewState -> renderViewState(viewState) }
            }
        }
    }

    private fun renderViewState(viewState: ProfileViewModel.UserViewState) {
        when (viewState) {
            is ProfileViewModel.UserViewState.Loading -> {
                viewBinding.profileImage.isVisible = false
                viewBinding.nameTextView.isVisible = false
                viewBinding.infoTextView.isVisible = false

                viewBinding.progressBar.isVisible = true
            }
            is ProfileViewModel.UserViewState.Profile -> {

                viewBinding.profileImage.isVisible = true
                viewBinding.nameTextView.isVisible = true
                viewBinding.infoTextView.isVisible = true

                val profile = viewState.data
                val avatarUrl = profile.avatarUrl
                if (avatarUrl != null) {
                    Glide.with(viewBinding.profileImage)
                        .load(avatarUrl)
                        .circleCrop()
                        .into(viewBinding.profileImage)
                } else {
                    viewBinding.profileImage.setImageResource(R.drawable.ic_person_white_24dp)
                    viewBinding.profileImage.setBackgroundColor(
                        resources.getColor(
                            R.color.black,
                            null
                        )
                    )
                }
                viewBinding.nameTextView.text = "${profile.firstName} ${profile.lastName}"
                viewBinding.infoTextView.text = buildString {
                    appendLine("Username: ${profile.userName}")
                    appendLine("Age: ${profile.age}")
                    appendLine("Group: ${profile.groupName}")
                }
                viewBinding.progressBar.isVisible = false
            }
        }
    }
}