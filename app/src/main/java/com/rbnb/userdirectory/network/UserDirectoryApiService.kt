package com.rbnb.userdirectory.network

import com.rbnb.userdirectory.user_list.User
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface UserDirectoryApiService {
    @GET("users")
    suspend fun getUsers(): List<User>
}

object UserDirectoryApi {
    val retrofitService: UserDirectoryApiService by lazy { retrofit.create(UserDirectoryApiService::class.java) }
}