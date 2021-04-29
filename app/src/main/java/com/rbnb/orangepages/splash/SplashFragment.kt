package com.rbnb.orangepages.splash

import android.animation.Animator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.rbnb.orangepages.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : Fragment() {

    private val viewModel: SplashViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSplashBinding.inflate(inflater, container, false)
        binding.animationViewSplashLogo.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(p0: Animator?) {
            }

            override fun onAnimationEnd(p0: Animator?) {
                viewModel.onAnimationEnd()
            }

            override fun onAnimationCancel(p0: Animator?) {
            }

            override fun onAnimationRepeat(p0: Animator?) {
            }
        })
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.navigateToLogin.observe(viewLifecycleOwner, {
            navigateToLogin(it)
        })

        viewModel.navigateToUserList.observe(viewLifecycleOwner, {
            navigateToUserList(it)
        })
    }

    private fun navigateToLogin(shouldNavigate: Boolean?) {
        shouldNavigate?.let {
            val action = SplashFragmentDirections.actionSplashFragmentToLoginFragment()
            findNavController().navigate(action)
            viewModel.onLoginNavigated()
        }
    }

    private fun navigateToUserList(shouldNavigate: Boolean?) {
        shouldNavigate?.let {
            val action = SplashFragmentDirections.actionSplashFragmentToUserListFragment()
            findNavController().navigate(action)
            viewModel.onUserDetailsNavigated()
        }
    }
}