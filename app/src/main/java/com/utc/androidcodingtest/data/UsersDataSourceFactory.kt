package com.utc.androidcodingtest.data

import androidx.paging.DataSource
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource

class UsersDataSourceFactory : DataSource.Factory<Int, User>() {

    //creating the mutable live data
    val usersLiveDataSource = MutableLiveData<PageKeyedDataSource<Int, User>>()

    override fun create(): DataSource<Int, User> {
        //getting our data source object
        val userDataSource = UserDataSource()
        //posting the datasource to get the values
        usersLiveDataSource.postValue(userDataSource)
        //returning the datasource
        return userDataSource
    }

}