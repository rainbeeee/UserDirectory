package com.rbnb.orangepages

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rbnb.orangepages.database.account.AccountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val repository: AccountRepository) : ViewModel() {

    private val _navigateToLogin = MutableLiveData<Boolean?>()
    val navigateToLogin: LiveData<Boolean?>
        get() = _navigateToLogin

    private val _navigateToUserList = MutableLiveData<Boolean?>()
    val navigateToUserList: LiveData<Boolean?>
        get() = _navigateToUserList

    fun onAnimationEnd() {
        when (repository.isSignedIn()) {
            true -> _navigateToUserList.value = true
            false -> _navigateToLogin.value = true
        }
    }

    fun doneNavigatingToLogin() {
        _navigateToLogin.value = null
    }

    fun doneNavigatingToUserList() {
        _navigateToUserList.value = null
    }
}