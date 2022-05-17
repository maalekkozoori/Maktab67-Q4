package com.example.maktab_q4.ui.userlistfromdatabase

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.maktab_q4.R
import com.example.maktab_q4.databinding.ItemUserBinding
import com.example.maktab_q4.model.localmodel.UserDB
import com.example.maktab_q4.model.networkmodel.UserRespons


class UserDBAdapter(private val onClick: (UserDB) -> Unit) :
    ListAdapter<UserDB, UserDBAdapter.MyViewHolder>(UserDiffCallback) {

    class MyViewHolder(private val binding: ItemUserBinding,private var onClick: (UserDB) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {

        fun mBind(userDB: UserDB) = binding.apply {
            tvFirstName.text = userDB.firstName
            tvLastName.text = userDB.lastName
            //tvHobbiesList.text = userDB.hobbies.toString()
            Glide.with(itemView.context)
                .load(userDB.image)
                .placeholder(R.drawable.ic_baseline_person_pin_24)
                .into(imgProfile)
            binding.root.setOnClickListener{
                onClick(userDB)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(ItemUserBinding.inflate(LayoutInflater.from(parent.context),parent,false),onClick)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.mBind(getItem(position))
    }


}



object UserDiffCallback : DiffUtil.ItemCallback<UserDB>() {

    override fun areItemsTheSame(oldItem: UserDB, newItem: UserDB): Boolean {
        return oldItem._id == newItem._id
    }

    override fun areContentsTheSame(oldItem: UserDB, newItem: UserDB): Boolean {
        return oldItem == newItem
    }


}
