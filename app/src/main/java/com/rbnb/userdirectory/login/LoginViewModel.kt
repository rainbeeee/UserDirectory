package com.rbnb.userdirectory.login

import androidx.lifecycle.*
import timber.log.Timber

class LoginViewModel : ViewModel() {

    val username = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    private val loginCredentials = MediatorLiveData<LoginCredentials>()

    val loginEnable: LiveData<Boolean> = Transformations.map(loginCredentials) {
        Timber.d("login credentials valid: ${it.isValid}")
        it.isValid
    }

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
}