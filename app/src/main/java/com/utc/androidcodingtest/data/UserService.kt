package com.utc.androidcodingtest.data

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface UserService {

    @GET("users")
    fun getUsers(@Query("page") page: Int,@Query("per_page") perPage: Int): Call<UserResponse>

    @POST("users")
    fun createUser(@Body user:User): Call<User>

}