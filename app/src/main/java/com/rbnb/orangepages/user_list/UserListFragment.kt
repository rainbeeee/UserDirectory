package com.rbnb.orangepages.user_list

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.rbnb.orangepages.R
import com.rbnb.orangepages.databinding.FragmentUserListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserListFragment : Fragment() {

    private val viewModel: UserListViewModel by viewModels()
    private lateinit var binding: FragmentUserListBinding
    private val adapter by lazy {
        UserListAdapter(UserItemListener { user ->
            viewModel.onUserClicked(user)
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserListBinding.inflate(layoutInflater, container, false)
        binding.viewModel = viewModel
        binding.recyclerViewUsers.adapter = adapter
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()

        viewModel.users.observe(viewLifecycleOwner, { users ->
            adapter.submitList(users)
        })

        viewModel.navigateToUserDetails.observe(viewLifecycleOwner, { user ->
            navigateToUserDetails(user)
        })

        viewModel.navigateToLogin.observe(viewLifecycleOwner, {
            navigateToLogInt(it)
        })
    }

    private fun initToolbar() {
        binding.toolbarUserList.inflateMenu(R.menu.menu_login)
        binding.toolbarUserList.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.menuLogOut -> {
                    viewModel.onLogOut()
                    true
                }
                else -> false
            }
        }
    }

    private fun navigateToUserDetails(user: User?) {
        user?.let {
            val action =
                UserListFragmentDirections.actionUserListFragmentToUserDetailsFragment(user)
            findNavController().navigate(action)
            viewModel.onUserDetailsNavigated()
        }
    }

    private fun navigateToLogInt(shouldNavigate: Boolean?) {
        shouldNavigate?.let {
            val action =
                UserListFragmentDirections.actionUserListFragmentToLoginFragment()
            findNavController().navigate(action)
            viewModel.onLoginNavigated()
        }
    }
}