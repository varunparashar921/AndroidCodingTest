package com.utc.androidcodingtest.data

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserDataSource : PageKeyedDataSource<Int, User>() {

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, User>) {
        RetrofitClient.instance.api.getUsers(FIRST_PAGE, PAGE_SIZE)
            .enqueue(object : Callback<UserResponse> {
                override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                    val userResponse = response.body()
                    Log.d("Response", "Success: ${Gson().toJson(userResponse)}")
                    if (userResponse != null) {
                        callback.onResult(
                            userResponse.data as MutableList<User>, null,
                            if (userResponse.page != userResponse.totalPages) FIRST_PAGE + 1 else null
                        )
                    }
                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    Log.d("Response", "Error: $t")
                }
            })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, User>) {
        RetrofitClient.instance.api.getUsers(FIRST_PAGE, PAGE_SIZE)
            .enqueue(object : Callback<UserResponse> {
                override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                    val userResponse = response.body()
                    if (userResponse != null) {
                        Log.d("Response", "${Gson().toJson(userResponse)}")
                        //if the response has next page
                        //incrementing the next page number
                        val key = if (userResponse.page != userResponse.totalPages) params.key + 1 else null

                        //passing the loaded data and next page value
                        callback.onResult(userResponse.data as MutableList<User>, key)
                    }
                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    Log.d("Response", "Error: $t")
                }
            })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, User>) {
    }

    companion object {
        //the size of a page that we want
        val PAGE_SIZE = 5
        //we will start from the first page which is 1
        val FIRST_PAGE = 1
    }

}