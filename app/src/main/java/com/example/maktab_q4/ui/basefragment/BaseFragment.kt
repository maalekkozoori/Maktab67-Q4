package com.example.maktab_q4.ui.basefragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.maktab_q4.R
import com.example.maktab_q4.databinding.FragmentBasecontainerBinding

class BaseFragment:Fragment(R.layout.fragment_basecontainer) {

    private var _binding : FragmentBasecontainerBinding? = null
    private val binding : FragmentBasecontainerBinding
        get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentBasecontainerBinding.bind(view)



    }
}