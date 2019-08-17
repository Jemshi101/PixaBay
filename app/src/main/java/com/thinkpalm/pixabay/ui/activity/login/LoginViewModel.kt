package com.thinkpalm.pixabay.ui.activity.login

import android.text.TextUtils
import android.util.Patterns
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.thinkpalm.pixabay.R
import com.thinkpalm.pixabay.core.App
import com.thinkpalm.pixabay.core.Config
import com.thinkpalm.pixabay.model.core.MessageBean
import com.thinkpalm.pixabay.network.DataManager
import com.thinkpalm.pixabay.network.models.login.LoginRequest
import com.thinkpalm.pixabay.network.models.login.LoginResponse
import com.thinkpalm.pixabay.sharedpreference.LoginPrefHandler
import com.thinkpalm.pixabay.ui.core.BaseViewModel
import com.thinkpalm.pixabay.utils.NullCheckUtil

/**
 * Created by Jemsheer K D on 21 June, 2019.
 * Package com.thinkpalm.pixabay.ui.activity.login
 * Project Pixabay
 */
class LoginViewModel : BaseViewModel() {
    var email = ObservableField("")
    var password = ObservableField("")
    var isRememberMe = ObservableBoolean(true)

    fun performLogin(): LiveData<LoginResponse?> {

        val loginRequest = LoginRequest()
        loginRequest.email = NullCheckUtil.getNonNull(email.get())
        loginRequest.password = NullCheckUtil.getNonNull(password.get())

        return Transformations.map(DataManager.performLogin(loginRequest)) {
            var loginResponse: LoginResponse? = null
            it?.let {
                if (it.status && it.responseBody != null) {
                    loginResponse = it.responseBody
                }
            }
            return@map loginResponse
        }
    }

    fun validateLogin(): Boolean {

        if (TextUtils.isEmpty(email.get())) {
            showMessage.value =
                MessageBean(App.getInstance().getString(R.string.message_please_enter_a_valid_email_address))
            return false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email.get()).matches()) {
            showMessage.value =
                MessageBean(App.getInstance().getString(R.string.message_please_enter_a_valid_email_address))
            return false
        }

        if (TextUtils.isEmpty(password.get())) {
            showMessage.value =
                MessageBean(App.getInstance().getString(R.string.message_please_enter_the_password))
            return false
        }

        return true
    }

    fun saveLoginData(loginResponse: LoginResponse) {
        Config.getInstance().saveLogin(loginResponse)
        if (isRememberMe.get()) {
            LoginPrefHandler.saveLoginData(loginResponse)
        }
    }
}