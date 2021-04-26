package com.rbnb.userdirectory.user_list

import androidx.lifecycle.*
import kotlinx.coroutines.launch

class UserListViewModel(private val repository: UserListRepository) : ViewModel() {

    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>>
        get() = _users

    init {
        getUserList()
    }

    private fun getUserList() {
        viewModelScope.launch {
            when (val response = repository.getUserList()) {
                emptyList<User>() -> {
                    _users.value = ArrayList()
                }
                else -> {
                    _users.value = response
                }
            }
        }
    }
}

class UserListViewModelFactory(private val repository: UserListRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UserListViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}