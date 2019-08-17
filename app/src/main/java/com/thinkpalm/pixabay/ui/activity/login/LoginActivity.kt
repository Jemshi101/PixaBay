package com.thinkpalm.pixabay.ui.activity.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.thinkpalm.pixabay.R
import com.thinkpalm.pixabay.databinding.ActivityLoginBinding
import com.thinkpalm.pixabay.network.models.login.LoginResponse
import com.thinkpalm.pixabay.ui.activity.search.SearchActivity
import com.thinkpalm.pixabay.ui.core.BaseActivity

class LoginActivity : BaseActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel
    private lateinit var handler: LoginHandler


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        handler = LoginHandler(object : LoginHandler.LoginHandlerListener {
            override fun performLogin(view: View) {
                if (viewModel.validateLogin()) {
                    initLogin()
                }
            }
        })

        binding.viewModel = viewModel
        binding.handler = handler
        initViews()
    }

    private fun initLogin() {
        val liveData = viewModel.performLogin()
        liveData.observe(this,
            Observer<LoginResponse?> {
                it?.let {
                    liveData.removeObservers(this)
                    viewModel.saveLoginData(it)
                    navigateToSearch()
                }
            })

    }

    private fun initViews() {
        setProgressVisibilityStatus(viewModel.progressStatus)
        setMessageBox(binding.root, viewModel.showMessage)

    }

    private fun navigateToSearch() {
        startActivity(Intent(this, SearchActivity::class.java))
        finish();
    }
}
