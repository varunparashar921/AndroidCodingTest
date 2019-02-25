package com.utc.androidcodingtest.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.utc.androidcodingtest.data.User
import com.utc.androidcodingtest.data.UserDataSource
import com.utc.androidcodingtest.data.UsersDataSourceFactory

class UsersListViewModel : ViewModel() {

    //creating livedata for PagedList  and PagedKeyedDataSource
    var usersPagedList: LiveData<PagedList<User>>? = null
    var liveDataSource: LiveData<PageKeyedDataSource<Int, User>>? = null

    init {
        //getting our data source factory
        val itemDataSourceFactory = UsersDataSourceFactory()

        //getting the live data source from data source factory
        liveDataSource = itemDataSourceFactory.usersLiveDataSource

        //Getting PagedList config
        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(UserDataSource.PAGE_SIZE).build()

        //Building the paged list
        usersPagedList = LivePagedListBuilder(itemDataSourceFactory, pagedListConfig).build()
    }
}