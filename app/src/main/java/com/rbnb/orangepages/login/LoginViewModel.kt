package com.rbnb.orangepages.login

import androidx.lifecycle.*
import com.rbnb.orangepages.database.account.Account
import com.rbnb.orangepages.database.account.AccountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repository: AccountRepository) : ViewModel() {

    val username = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    private val loginCredentials = MediatorLiveData<LoginCredentials>()

    val loginEnable: LiveData<Boolean> = Transformations.map(loginCredentials) {
        Timber.d("login: credentials valid is ${it.isValid}")
        it.isValid
    }

    private val _navigateToUserList = MutableLiveData<Boolean?>()
    val navigateToUserList: LiveData<Boolean?>
        get() = _navigateToUserList

    init {
        loginCredentials.addSource(username) {
            loginCredentials.value = initLoginCredentials()
        }

        loginCredentials.addSource(password) {
            loginCredentials.value = initLoginCredentials()
        }
    }

    private fun initLoginCredentials() = LoginCredentials(
        username.value,
        password.value
    )

    fun authenticateAccount() {
        viewModelScope.launch {
            val account: Account? = repository.getAccount(username.value, password.value)
            Timber.d("login: username = ${username.value}, password = ${password.value}")
            Timber.d("login: account = $account")
            if (account != null) {
                _navigateToUserList.value = true
            }
        }
    }

    fun doneNavigating() {
        _navigateToUserList.value = null
    }

    fun onKeepSignedIn(shouldKeepSignedIn: Boolean) {
        Timber.d("login: shouldKeepSignedIn is $shouldKeepSignedIn")
    }
}