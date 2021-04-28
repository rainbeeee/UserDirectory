package com.rbnb.orangepages.user_list

import com.rbnb.orangepages.network.OrangePagesApiService
import timber.log.Timber
import javax.inject.Inject

class UserListRepository @Inject constructor(private val apiService: OrangePagesApiService) {

    suspend fun getUserList(): List<User> {
        return try {
            val users = apiService.getUsers()
            Timber.d("users: $users")
            users
        } catch (e: Exception) {
            Timber.d("users: Failure. {${e.localizedMessage}}")
            ArrayList()
        }
    }
}