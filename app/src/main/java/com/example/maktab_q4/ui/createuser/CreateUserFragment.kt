package com.example.maktab_q4.ui.createuser

import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.maktab_q4.R
import com.example.maktab_q4.databinding.FragmentCreateuserBinding
import com.example.maktab_q4.ui.userslist.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import ir.mohsenafshar.apps.mkbarchitecture.data.remote.model.UserReqBody

@AndroidEntryPoint
class CreateUserFragment : Fragment(R.layout.fragment_createuser) {

    private var _binding: FragmentCreateuserBinding? = null
    private val binding: FragmentCreateuserBinding
        get() = _binding!!

    lateinit var hobbiesList: MutableList<String>
    val viewModel: UserViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentCreateuserBinding.bind(view)

        hobbiesList = mutableListOf()

        viewModel.createdUserId.observe(viewLifecycleOwner){
            Toast.makeText(requireContext(), "User Created Successfully!", Toast.LENGTH_SHORT)
                .show()
            findNavController().navigate(R.id.action_createUser_to_userListFragment)
        }

        with(binding) {
            chMovieHobbies.setOnClickListener {onCheckboxClicked(it)}
            chProgrammingHobbies.setOnClickListener {onCheckboxClicked(it)}
            chSportHobbies.setOnClickListener {onCheckboxClicked(it)}
            btnCreate.setOnClickListener {
                if (!edFirstName.text.isNullOrEmpty() &&
                    !edLastName.text.isNullOrEmpty() &&
                    !edNationalCode.text.isNullOrEmpty()){
                    val firstName = edFirstName.text.toString()
                    val lastName = edLastName.text.toString()
                    val nationalCode = edNationalCode.text.toString()
                    createUser(UserReqBody(firstName,hobbiesList,lastName,nationalCode))
                }else{
                    Toast.makeText(
                        requireContext(),
                        "Fields should not to be empty!",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }
        }


    }

    fun onCheckboxClicked(view: View) {
        if (view is CheckBox) {
            val checked: Boolean = view.isChecked

            when (view.id) {
                R.id.chMovieHobbies -> {
                    if (checked) {
                        hobbiesList.add("Movies")
                    }else{
                        hobbiesList.remove("Movies")
                    }
                }
                R.id.chProgrammingHobbies -> {
                    if (checked) {
                        hobbiesList.add("Programming")
                    } else {
                        hobbiesList.remove("Programming")
                    }
                }
                R.id.chSportHobbies -> {
                    if (checked){
                        hobbiesList.add("Sport")
                    }else{
                        hobbiesList.remove("Sport")
                    }
                }
            }
        }
    }

    private fun createUser(userReqBody: UserReqBody) {
        viewModel.createUser(userReqBody)
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}