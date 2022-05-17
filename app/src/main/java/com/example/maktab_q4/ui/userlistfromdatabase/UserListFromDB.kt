package com.example.maktab_q4.ui.userlistfromdatabase

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.maktab_q4.R
import com.example.maktab_q4.databinding.FragmentUserlistfromdbBinding
import com.example.maktab_q4.ui.adapter.UserAdapter
import com.example.maktab_q4.ui.userslist.UserListFragmentDirections
import com.example.maktab_q4.ui.userslist.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UserListFromDB: Fragment(R.layout.fragment_userlistfromdb) {

    private var _binding : FragmentUserlistfromdbBinding? = null
    private val binding : FragmentUserlistfromdbBinding
        get() = _binding!!


    lateinit var adapter: UserDBAdapter
    val viewModel: UserViewModel by viewModels<UserViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentUserlistfromdbBinding.bind(view)


        adapter = UserDBAdapter(onClick ={userDB ->
            findNavController().navigate(UserListFromDBDirections.actionUserListFromDBToDBUserDetailsFragment(userDB._id))
        })

        binding.rcUserList.adapter= adapter

        observers()

    }

    private fun observers() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.databaseUserList.collectLatest {
                    adapter.submitList(it)
                }
            }
        }
    }
}