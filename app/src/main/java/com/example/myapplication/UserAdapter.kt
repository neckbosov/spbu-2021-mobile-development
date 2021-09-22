package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

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

    val userList: List<User> = listOf(
        User("kek", "Olya", "Left"),
        User("kek", "Andrei", "Right"),
        User("kek", "Nikita", "Center")
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //holder.avatarImageView.setImageBitmap(userList[position].avatar)
        holder.avatarImageView.setImageResource(R.mipmap.ic_launcher)
        holder.groupNameView.text = userList[position].groupName
        holder.nameView.text = userList[position].name
    }

    override fun getItemCount(): Int = userList.size
}