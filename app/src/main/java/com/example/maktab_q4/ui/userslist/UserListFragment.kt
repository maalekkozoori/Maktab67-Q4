package com.example.maktab_q4.ui.userslist

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.maktab_q4.R
import com.example.maktab_q4.databinding.FragmentUserlistBinding
import com.example.maktab_q4.model.localmodel.UserDB
import com.example.maktab_q4.model.networkmodel.UserRespons
import com.example.maktab_q4.ui.adapter.SwipeToDeleteCallback
import com.example.maktab_q4.ui.adapter.UserAdapter
import com.example.maktab_q4.utility.ResultWrapper
import com.example.maktab_q4.utility.convertUserResponsToUserDB
import dagger.hilt.android.AndroidEntryPoint
import ir.mohsenafshar.apps.mkbarchitecture.ui.users.RecyclerAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class UserListFragment:Fragment(R.layout.fragment_userlist) {

    val viewModel: UserViewModel by viewModels()
    private lateinit var recyclerAdapter: RecyclerAdapter

    lateinit var adapter: UserAdapter

    private var _binding : FragmentUserlistBinding? = null
    private val binding : FragmentUserlistBinding
        get() = _binding!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentUserlistBinding.bind(view)


        val swipeToDeleteCallback = object :SwipeToDeleteCallback(){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                addUserToDataBase(adapter.currentList[position])
                Toast.makeText(requireContext(), adapter.currentList[position].firstName, Toast.LENGTH_SHORT).show()



            }

        }

        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(binding.rcUserList)


        adapter = UserAdapter(onClick ={userRespons ->
            findNavController().navigate(UserListFragmentDirections.actionUserListFragmentToUserDetailsFragment(userRespons._id))
        })
        binding.rcUserList.adapter= adapter




        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.userListFlow.collect{
                    when(it){
                        is ResultWrapper.Loading -> "Loading"
                        is ResultWrapper.Success -> {
                            adapter.submitList(it.value)
                            if (it.value.isNotEmpty()){
                                //binding.stateView.onSuccess()
                            }else{
                                "binding.stateView.onEmpty()"
                            }
                        }
                        is ResultWrapper.Error -> "Error"
                    }
                }
            }
        }




        binding.fab.setOnClickListener { view ->
            findNavController().navigate(R.id.action_userListFragment_to_createUser)
        }



        //addUserToDataBase()



    }

    fun addUserToDataBase(userRespons: UserRespons){
        viewModel.addUserToDataBase(convertUserResponsToUserDB(userRespons))
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}