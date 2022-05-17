package com.example.maktab_q4.ui.userdetails

import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.CheckBox
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.maktab_q4.R
import com.example.maktab_q4.databinding.FragmentDbuserdetailsBinding
import com.example.maktab_q4.ui.userslist.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import ir.mohsenafshar.apps.mkbarchitecture.data.remote.model.UserReqBody

@AndroidEntryPoint
class DBUserDetailsFragment: Fragment(R.layout.fragment_dbuserdetails){

    private var _binding : FragmentDbuserdetailsBinding? = null
    private val binding : FragmentDbuserdetailsBinding
        get() = _binding!!

    lateinit var hobbiesList: MutableList<String>

    private val args by navArgs<UserDetailsFragmentArgs>()

    val viewModel: UserViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDbuserdetailsBinding.bind(view)

        hobbiesList = mutableListOf()
        init()

        viewModel.createdUserId.observe(viewLifecycleOwner){
            Toast.makeText(requireContext(), "User Created Successfully!", Toast.LENGTH_SHORT)
                .show()
            findNavController().navigate(R.id.action_DBUserDetailsFragment_to_userListFragment)
        }

        with(binding){
            viewModel.userDetails.observe(viewLifecycleOwner) {
                tvFirstName.setText(it.firstName)
                tvLastName.setText(it.lastName)
                tvNationalCode.setText(it.nationalCode)
                Glide.with(imageView.context)
                    .load(it.image)
                    .placeholder(R.drawable.ic_baseline_person_pin_24)
                    .into(imageView)
            }


            chMovieHobbies.setOnClickListener {onCheckboxClicked(it)}
            chProgrammingHobbies.setOnClickListener {onCheckboxClicked(it)}
            chSportHobbies.setOnClickListener {onCheckboxClicked(it)}
            btnCreateNewUser.setOnClickListener {
                if (!tvFirstName.text.isNullOrEmpty() &&
                    !tvLastName.text.isNullOrEmpty() &&
                    !tvNationalCode.text.isNullOrEmpty()){
                    val firstName = tvFirstName.text.toString()
                    val lastName = tvLastName.text.toString()
                    val nationalCode = tvNationalCode.text.toString()
                    createUser(UserReqBody(firstName,hobbiesList,lastName,nationalCode))
                }else{
                    Toast.makeText(
                        requireContext(),
                        "Fields should not be empty!",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }

            btnCancel.setOnClickListener {
                findNavController().navigate(R.id.action_DBUserDetailsFragment_to_userListFromDB)
            }
        }



    }

    private fun createUser(userReqBody: UserReqBody) {
        viewModel.createUser(userReqBody)
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

    private fun init() {
        viewModel.getUserDetails(args.userId)

    }




    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}