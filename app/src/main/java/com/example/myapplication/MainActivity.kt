package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    val viewModel: MainViewModel by viewModels()

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val userAdapter = setupRecyclerView()
        val userRecyclerView = findViewById<View>(R.id.recyclerView).also {
            it.isVisible = false
        }
        val progressBar = findViewById<View>(R.id.progressBar).also {
            it.isVisible = true
        }
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.viewState.collect {
                    when (it) {
                        is MainViewModel.ViewState.Data -> {
                            userAdapter.userList = it.users
                            userAdapter.notifyDataSetChanged()
                            progressBar.isVisible = false
                            userRecyclerView.isVisible = true
                        }
                        is MainViewModel.ViewState.Loading -> {
                            userAdapter.userList = listOf()
                            progressBar.isVisible = true
                            userRecyclerView.isVisible = false
                        }
                    }
                }
            }
        }
    }


    private fun setupRecyclerView(): UserAdapter {
        val usersRecyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val userAdapter = UserAdapter()
        usersRecyclerView.adapter = userAdapter
        return userAdapter
    }
}