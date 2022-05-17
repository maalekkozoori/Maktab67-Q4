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
import androidx.core.graphics.drawable.toBitmap
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
import com.example.maktab_q4.utility.toByteArray
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.MediaType
import okhttp3.MultipartBody

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
                Glide.with(this)
                    .load(it)
                    .placeholder(R.drawable.ic_baseline_person_pin_24)
                    .into(binding.imageView)

            })

        var getImageFromCamera = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    cameraImage(result.data)
                }
            }

        viewModel.imageUrl.observe(viewLifecycleOwner) {
         /*   Toast.makeText(requireContext(), "Image Uploaded!", Toast.LENGTH_SHORT).show()
            Glide.with(view.context)
                .load(it)
                .placeholder(R.drawable.ic_baseline_person_pin_24)
                .into(binding.imageView)

            */
        }


        with(binding){
            viewModel.user.observe(viewLifecycleOwner, Observer {
                tvFirstName.text = it.firstName
                tvLastName.text = it.lastName
                tvNationalCode.text = it.nationalCode
                Glide.with(this@UserDetailsFragment)
                    .load(it.image)
                    .placeholder(R.drawable.ic_baseline_person_pin_24)
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

            btnApply.setOnClickListener {
                val convertToByteArray=context?.contentResolver?.openInputStream(imageByteArray)?.readBytes()
                convertToByteArray?.let { byteArray ->
                     uploadImage(byteArray)
                }
            }
            val b = imageView.background.toBitmap()
        }


    }

    fun uploadImage(image: ByteArray){
        val body= MultipartBody.create(MediaType.parse("image/*"),image)
        val request = MultipartBody.Part.createFormData("image","${args.userId}.jpg",body)

        viewModel.uploadImage(args.userId,request)

    }



    private fun init()= binding.apply {
        viewModel.getUser(args.userId)

    }

    private fun cameraImage(intent: Intent?) {
        val bitmap = intent?.extras?.get("data") as Bitmap
        val b = bitmap.toByteArray()
        view?.let { Glide.with(it.context)
            .load(bitmap)
            .placeholder(R.drawable.ic_baseline_person_pin_24)
            .into(binding.imageView)
        }
    }


    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}