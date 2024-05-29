package com.example.viewmodelexample.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.viewmodelexample.R
import com.example.viewmodelexample.models.User

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserHolder>() {

    private var users: List<User> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        return UserHolder(view)
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        holder.name.text = users[position].name
        holder.description.text = users[position].description
    }

    override fun getItemCount(): Int {
        return  users.size
    }

    fun refreshUsers(users: List<User>) {
        this.users = users
        notifyDataSetChanged()
    }

    class UserHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name : TextView = itemView.findViewById(R.id.userName)
        val description : TextView = itemView.findViewById(R.id.userDescription)
    }
}