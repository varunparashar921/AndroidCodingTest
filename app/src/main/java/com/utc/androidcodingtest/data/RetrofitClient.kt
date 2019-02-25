package com.utc.androidcodingtest.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient private constructor() {

    private val retrofit: Retrofit

    val api: UserService
        get() = retrofit.create(UserService::class.java)

    init {
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    companion object {

        private val BASE_URL = "https://reqres.in/api/"
        private var mInstance: RetrofitClient? = null

        val instance: RetrofitClient
            @Synchronized get() {
                if (mInstance == null) {
                    mInstance = RetrofitClient()
                }
                return mInstance as RetrofitClient
            }
    }
}