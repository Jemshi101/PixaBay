package com.thinkpalm.orchid_android.ui.activity.login

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.thinkpalm.orchid_android.R
import com.thinkpalm.orchid_android.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel
    private lateinit var handler: LoginHandler


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)

        handler = LoginHandler(object : LoginHandler.LoginHandlerListener {
            override fun performLogin(view: View) {
                viewModel.performLogin()

            }
        })

        binding.viewModel = viewModel
        binding.handler = handler
        initViews()
    }

    private fun initViews() {


    }
}
