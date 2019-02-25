package com.utc.androidcodingtest.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.utc.androidcodingtest.data.RetrofitClient
import com.utc.androidcodingtest.data.User
import com.utc.androidcodingtest.data.UserDataSource
import com.utc.androidcodingtest.data.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterUserViewModel : ViewModel() {

    var errorLiveData: MutableLiveData<String> = MutableLiveData()
    var reponseLiveDate: MutableLiveData<User> = MutableLiveData()

    fun registerUser(user: User) = RetrofitClient.instance.api.createUser(user)
        .enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                val user = response.body()
                Log.d("Response", "Success: ${Gson().toJson(user)}")
                if (user != null) {
                    reponseLiveDate.value = user
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.d("Response", "Error: $t")
                errorLiveData.value = "error"
            }
        })

}