package com.rbnb.userdirectory.user_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.rbnb.userdirectory.UserDirectoryApplication
import com.rbnb.userdirectory.databinding.FragmentUserListBinding

class UserListFragment : Fragment() {

    private val viewModel: UserListViewModel by viewModels {
        UserListViewModelFactory((activity?.application as UserDirectoryApplication).userListRepository)
    }

    private val adapter by lazy {
        UserListAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentUserListBinding.inflate(layoutInflater, container, false)
        binding.viewModel = viewModel
        binding.recyclerViewUsers.adapter = adapter
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.users.observe(viewLifecycleOwner, { users ->
            adapter.submitList(users)

        })
    }
}