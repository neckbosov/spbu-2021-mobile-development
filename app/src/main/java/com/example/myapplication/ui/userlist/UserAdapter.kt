package com.example.myapplication.ui.userlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.entity.User

class UserAdapter : RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val avatarImageView = itemView.findViewById<ImageView>(R.id.imageView)
        val nameView = itemView.findViewById<TextView>(R.id.nameView)
        val groupNameView = itemView.findViewById<TextView>(R.id.groupNameView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_user,
            parent,
            false
        )
        return ViewHolder(view)
    }

    var userList: List<User> = listOf()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.avatarImageView)
            .load(userList[position].avatarUrl)
            .circleCrop()
            .into(holder.avatarImageView)
        holder.nameView.text = userList[position].name
        holder.groupNameView.text = userList[position].email
    }

    override fun getItemCount(): Int = userList.size
}