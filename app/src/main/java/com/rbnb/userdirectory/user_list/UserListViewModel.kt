package com.rbnb.userdirectory.user_list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.rbnb.userdirectory.network.UserDirectoryApi
import kotlinx.coroutines.launch
import timber.log.Timber

class UserListViewModel : ViewModel() {

    private val _users = MutableLiveData<List<User>>()

    init {
        getUserList()
    }

    private fun getUserList() {
        viewModelScope.launch {
            try {
                _users.value = UserDirectoryApi.retrofitService.getUsers()
//                _status.value = DONE
                Timber.d("users: Success. ${_users.value}")
            } catch (e: Exception) {
                _users.value = ArrayList()
//                _status.value = ERROR
                Timber.d("users: Failure. {${e.localizedMessage}}")
            }
        }
    }
}

class UserListViewModelFactory() : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UserListViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}