package com.example.maktab_q4.ui.userdetails

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.maktab_q4.R
import com.example.maktab_q4.databinding.FragmentUserdetailsBinding
import com.example.maktab_q4.databinding.FragmentUserlistBinding
import com.example.maktab_q4.ui.userslist.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserDetailsFragment:Fragment(R.layout.fragment_userdetails) {
    private var _binding : FragmentUserdetailsBinding? = null
    private val binding : FragmentUserdetailsBinding
        get() = _binding!!

    private lateinit var imageByteArray: Uri

    val viewModel: UserViewModel by viewModels()
    private val args by navArgs<UserDetailsFragmentArgs>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentUserdetailsBinding.bind(view)

        init()

        var getImageFromGallery = registerForActivityResult(
            ActivityResultContracts.GetContent(),
            ActivityResultCallback {
                imageByteArray = it!!
                Log.d("TAG", imageByteArray.toString())
                binding.imageView.setImageURI(it)

            })

        var getImageFromCamera = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    cameraImage(result.data)
                }
            }


        with(binding){
            viewModel.user.observe(viewLifecycleOwner, Observer {
                tvFirstName.text = it.firstName
                tvLastName.text = it.lastName
                tvNationalCode.text = it.nationalCode
                Glide.with(this@UserDetailsFragment)
                    .load(it.image)
                    .into(imageView)

            })

            btnImageFromGallery.setOnClickListener {
                getImageFromGallery.launch("image/*")
            }

            btnImageFromCamera.setOnClickListener {
                val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                getImageFromCamera.launch(cameraIntent)
            }

            btnCancel.setOnClickListener {
                findNavController().navigate(R.id.action_userDetailsFragment_to_userListFragment)
            }
        }









    }



    private fun init()= binding.apply {
        viewModel.getUser(args.userId)

    }

    private fun cameraImage(intent: Intent?) {
        val bitmap = intent?.extras?.get("data") as Bitmap
        binding.imageView.setImageBitmap(bitmap)
    }


    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}