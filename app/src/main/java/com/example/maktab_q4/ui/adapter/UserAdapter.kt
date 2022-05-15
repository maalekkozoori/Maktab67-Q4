package com.example.maktab_q4.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.maktab_q4.databinding.ItemUserBinding
import com.example.maktab_q4.model.networkmodel.UserRespons
import ir.mohsenafshar.apps.mkbarchitecture.data.remote.model.UserResponse


class UserAdapter(private val onClick: (UserRespons) -> Unit) :
    ListAdapter<UserRespons, UserAdapter.MyViewHolder>(UserDiffCallback) {

    class MyViewHolder(private val binding: ItemUserBinding,private var onClick: (UserRespons) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {

        fun mBind(userRespons: UserRespons) = binding.apply {
            tvFirstName.text = userRespons.firstName
            tvLastName.text = userRespons.lastName
            tvHobbiesList.text = userRespons.hobbies.toString()
            binding.root.setOnClickListener{
                onClick(userRespons)
            }
        }

        init {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(ItemUserBinding.inflate(LayoutInflater.from(parent.context),parent,false),onClick)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.mBind(getItem(position))
    }


}



object UserDiffCallback : DiffUtil.ItemCallback<UserRespons>() {

    override fun areItemsTheSame(oldItem: UserRespons, newItem: UserRespons): Boolean {
        return oldItem._id == newItem._id
    }

    override fun areContentsTheSame(oldItem: UserRespons, newItem: UserRespons): Boolean {
        return oldItem == newItem
    }


}
