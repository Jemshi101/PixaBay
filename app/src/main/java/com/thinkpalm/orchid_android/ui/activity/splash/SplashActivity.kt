package com.thinkpalm.orchid_android.ui.activity.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.thinkpalm.orchid_android.R
import com.thinkpalm.orchid_android.databinding.ActivitySplashBinding
import com.thinkpalm.orchid_android.ui.activity.login.LoginActivity
import com.thinkpalm.orchid_android.ui.activity.search.SearchActivity

class SplashActivity : AppCompatActivity() {

    private lateinit var viewModel: SplashViewModel
    private val mHandler: Handler = Handler()
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)

        viewModel = ViewModelProviders.of(this).get(SplashViewModel::class.java)

        val playServiceStatus = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(applicationContext)
        if (playServiceStatus != ConnectionResult.SUCCESS) {
            GoogleApiAvailability.getInstance().getErrorDialog(this, playServiceStatus, -1).show()
        } else {
            initViews()
            mHandler.postDelayed(initTask, 1500)
        }

    }

    private fun initViews() {

        viewModel.isLoggedIn.observe(this, Observer<Boolean?> { isLoggedIn ->

            if (!viewModel.isInit) {
                if (isLoggedIn != null && isLoggedIn) {
                    navigateToHome()
                } else {
                    navigateToLogin()
                }
            }
        })
    }

    private val initTask = {
        viewModel.checkLoginStatus()
    }

    private fun navigateToLogin() {
        startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
        finish()
    }

    private fun navigateToHome() {
        startActivity(Intent(this@SplashActivity, SearchActivity::class.java))
        finish()
    }


}
