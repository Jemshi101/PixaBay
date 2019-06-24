package com.thinkpalm.orchid_android.ui.activity.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

/**
 * Created by Jemsheer K D on 21 June, 2019.
 * Package com.thinkpalm.orchid_android.ui.activity.splash
 * Project Orchid
 */
class SplashViewModel : ViewModel() {
    var isInit = true
    var isLoggedIn: MutableLiveData<Boolean> = MutableLiveData()


    fun checkLoginStatus() {
        val auth = FirebaseAuth.getInstance()
        isInit = false
        isLoggedIn.value = auth.currentUser != null
    }

}