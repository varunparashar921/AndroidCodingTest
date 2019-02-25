package com.utc.androidcodingtest

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import com.utc.androidcodingtest.data.User
import com.utc.androidcodingtest.databinding.ActivityRegisterUserBinding
import com.utc.androidcodingtest.viewmodels.RegisterUserViewModel
import com.utc.androidcodingtest.viewmodels.UsersListViewModel

class RegisterUserActivity : AppCompatActivity() {

    private var viewModel: RegisterUserViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityRegisterUserBinding = DataBindingUtil.setContentView(this, R.layout.activity_register_user)
        binding.user = User()
        binding.activity = this
        viewModel = ViewModelProviders.of(this).get(RegisterUserViewModel::class.java)
        viewModel?.errorLiveData?.observe(this, Observer { error ->
            val viewGroup = findViewById<ViewGroup>(android.R.id.content).getChildAt(0) as ViewGroup
            Snackbar.make(viewGroup, error, Snackbar.LENGTH_LONG).show()
        })
        viewModel?.reponseLiveDate?.observe(this, Observer { user ->
            val viewGroup = findViewById<ViewGroup>(android.R.id.content).getChildAt(0) as ViewGroup
            Snackbar.make(viewGroup, "User successfully registerd", Snackbar.LENGTH_LONG).show()
        })
    }

    fun onSaveUser(view: View, user: User) {
        if (TextUtils.isEmpty(user.firstName) && TextUtils.isEmpty(user.lastName)) {
            viewModel?.registerUser(user)
        }
    }
}
