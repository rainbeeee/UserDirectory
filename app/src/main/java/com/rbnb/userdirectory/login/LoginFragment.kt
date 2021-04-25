package com.rbnb.userdirectory.login

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.rbnb.userdirectory.R
import com.rbnb.userdirectory.databinding.FragmentLoginBinding

class LoginFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        binding.apply {
            this.viewModel = viewModel
            clickHandler = this@LoginFragment
            lifecycleOwner = viewLifecycleOwner
        }
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.buttonLogin -> navigateToUserList()
        }
    }

    private fun navigateToUserList() {
        val action = LoginFragmentDirections.actionLoginFragmentToUserListFragment()
        findNavController().navigate(action)
    }
}