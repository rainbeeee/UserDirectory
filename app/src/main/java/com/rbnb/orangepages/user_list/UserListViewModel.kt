package com.rbnb.orangepages.user_list

import androidx.lifecycle.*
import com.rbnb.orangepages.database.account.AccountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val repository: UserListRepository,
    private val accountRepository: AccountRepository
) : ViewModel() {

    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>>
        get() = _users

    private val _navigateToUserDetails = MutableLiveData<User?>()
    val navigateToUserDetails
        get() = _navigateToUserDetails

    private val _navigateToLogin = MutableLiveData<Boolean?>()
    val navigateToLogin
        get() = _navigateToLogin

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

    fun onUserClicked(user: User) {
        _navigateToUserDetails.value = user
    }

    fun onUserDetailsNavigated() {
        _navigateToUserDetails.value = null
    }

    fun onLogOut() {
        accountRepository.logOut()
        _navigateToLogin.value = true
    }

    fun onLoginNavigated() {
        _navigateToLogin.value = null
    }
}