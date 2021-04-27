package com.rbnb.userdirectory.user_list.user_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.rbnb.userdirectory.databinding.FragmentUserDetailsBinding

class UserDetailsFragment : Fragment() {

    private val safeArgs: UserDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentUserDetailsBinding.inflate(inflater, container, false)
        binding.user = safeArgs.user
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }
}