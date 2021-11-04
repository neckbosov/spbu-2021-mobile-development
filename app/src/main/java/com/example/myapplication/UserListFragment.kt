package com.example.myapplication

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class UserListFragment : Fragment(R.layout.fragment_user_list) {
    val viewModel: UserListViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userAdapter = setupRecyclerView(view)

        val userRecyclerView = view.findViewById<View>(R.id.recyclerView).also {
            it.isVisible = false
        }
        val progressBar = view.findViewById<View>(R.id.progressBar).also {
            it.isVisible = true
        }
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.viewState.collect {
                    when (it) {
                        is UserListViewModel.ViewState.Data -> {
                            userAdapter.userList = it.users
                            userAdapter.notifyItemChanged(R.id.recyclerView)
                            progressBar.isVisible = false
                            userRecyclerView.isVisible = true
                        }
                        is UserListViewModel.ViewState.Loading -> {
                            userAdapter.userList = listOf()
                            progressBar.isVisible = true
                            userRecyclerView.isVisible = false
                        }
                    }
                }
            }
        }
    }


    private fun setupRecyclerView(view: View): UserAdapter {
        val usersRecyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        val userAdapter = UserAdapter()
        usersRecyclerView.adapter = userAdapter
        return userAdapter
    }
}