package com.utc.androidcodingtest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.utc.androidcodingtest.adapter.UsersAdapter
import com.utc.androidcodingtest.databinding.ActivityUsersListBinding
import com.utc.androidcodingtest.viewmodels.UsersListViewModel

class UsersListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityUsersListBinding = DataBindingUtil.setContentView(this, R.layout.activity_users_list)
        setSupportActionBar(binding.toolbar)
        val adapter = UsersAdapter()
        subscribeUi(adapter, binding)
    }

    private fun subscribeUi(adapter: UsersAdapter, binding: ActivityUsersListBinding) {
        val viewModel = ViewModelProviders.of(this).get(UsersListViewModel::class.java)

        viewModel.usersPagedList?.observe(this, Observer { users ->
            Log.d("Response", "viewModel   userspagedList called:${users.size}")
            adapter.submitList(users)
        })
        viewModel.liveDataSource?.observe(this, Observer {
//            Log.d("Response", "viewModel   userspagedList called:${it.invalidate()}")
        })
        binding.contentMain.usersList.adapter = adapter
        binding.activity = this
    }

    fun createUser() {
        startActivity(Intent(this, RegisterUserActivity::class.java))
    }

}
