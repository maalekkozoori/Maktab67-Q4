package com.example.maktab_q4.ui.userslist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.maktab_q4.R
import com.example.maktab_q4.databinding.FragmentUserlistBinding
import ir.mohsenafshar.apps.mkbarchitecture.data.remote.model.UserResponse
import ir.mohsenafshar.apps.mkbarchitecture.ui.users.RecyclerAdapter


class UserListFragment:Fragment(R.layout.fragment_userlist) {

    val viewModel: UserViewModel by viewModels()
    private lateinit var recyclerAdapter: RecyclerAdapter

    lateinit var binding : FragmentUserlistBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentUserlistBinding.bind(view)

        val emptyList = emptyList<UserResponse>()
        recyclerAdapter = RecyclerAdapter(emptyList)
        viewModel.getUsersList()

        viewModel.userList.observe(viewLifecycleOwner, Observer {
            recyclerAdapter = RecyclerAdapter((it))
            recyclerAdapter.notifyDataSetChanged()
            initRecyclerView()

        })





    }

    private fun initRecyclerView() {
        binding.rcUserList.adapter = recyclerAdapter
        binding.rcUserList.layoutManager = LinearLayoutManager(requireContext())
    }
}