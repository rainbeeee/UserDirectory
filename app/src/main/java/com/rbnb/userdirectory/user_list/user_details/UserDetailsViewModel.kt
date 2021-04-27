package com.rbnb.userdirectory.user_list.user_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rbnb.userdirectory.user_list.User

class UserDetailsViewModel(val user: User) : ViewModel() {

    val lat: Double
        get() = user.address.geo.lat.toDouble()

    val long: Double
        get() = user.address.geo.lng.toDouble()

    val city: String
        get() = user.address.city
}

class UserDetailsViewModelFactory(private val user: User?) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserDetailsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return user?.let { UserDetailsViewModel(it) } as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}