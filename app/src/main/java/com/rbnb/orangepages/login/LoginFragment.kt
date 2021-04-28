package com.rbnb.orangepages.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.rbnb.orangepages.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.navigateToUserList.observe(viewLifecycleOwner, {
            if (it == true) {
                navigateToUserList()
            }
        })
        return binding.root
    }

    private fun navigateToUserList() {
        val action = LoginFragmentDirections.actionLoginFragmentToUserListFragment()
        findNavController().navigate(action)
        viewModel.doneNavigating()
    }
}