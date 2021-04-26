package com.rbnb.userdirectory.user_list

import com.rbnb.userdirectory.network.UserDirectoryApi
import timber.log.Timber

class UserListRepository {

    suspend fun getUserList(): List<User> {
        return try {
            val users = UserDirectoryApi.retrofitService.getUsers()
            Timber.d("users: $users")
            users
        } catch (e: Exception) {
            Timber.d("users: Failure. {${e.localizedMessage}}")
            ArrayList()
        }
    }
}