package com.rbnb.orangepages.network

import com.rbnb.orangepages.user_list.User
import retrofit2.http.GET

interface OrangePagesApiService {
    @GET("users")
    suspend fun getUsers(): List<User>
}