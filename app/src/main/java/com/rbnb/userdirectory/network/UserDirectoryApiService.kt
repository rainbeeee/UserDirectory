package com.rbnb.userdirectory.network

import com.rbnb.userdirectory.user_list.User
import retrofit2.http.GET

interface UserDirectoryApiService {
    @GET("users")
    suspend fun getUsers(): List<User>
}