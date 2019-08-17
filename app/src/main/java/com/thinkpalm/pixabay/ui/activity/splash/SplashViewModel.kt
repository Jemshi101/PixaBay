package com.thinkpalm.pixabay.ui.activity.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.thinkpalm.pixabay.sharedpreference.LoginPrefHandler

/**
 * Created by Jemsheer K D on 21 June, 2019.
 * Package com.thinkpalm.pixabay.ui.activity.splash
 * Project Pixabay
 */
class SplashViewModel : ViewModel() {
    var isInit = true
    var isLoggedIn: MutableLiveData<Boolean> = MutableLiveData()


    fun checkLoginStatus() {
        val loginData =  LoginPrefHandler.getLoginData()
        isInit = false
        isLoggedIn.value = loginData.isLoggedIn
    }

}