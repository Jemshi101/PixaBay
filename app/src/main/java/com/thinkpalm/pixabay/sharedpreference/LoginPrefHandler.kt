package com.thinkpalm.pixabay.sharedpreference

import com.thinkpalm.pixabay.network.models.login.LoginData
import com.thinkpalm.pixabay.network.models.login.LoginResponse

/**
 * Created by Jemsheer K D on 17 August, 2019.
 * Package com.thinkpalm.pixabay.sharedpreference
 * Project Orchid
 */
class LoginPrefHandler {

    companion object {
        @JvmStatic
        fun saveLoginData(loginResponse: LoginResponse) {
            val data = loginResponse.data;
            val handler = SharedPrefHandler.getInstance()

            handler.saveString(SharedPrefKeys.USER_ID, data.userID)
            handler.saveString(SharedPrefKeys.USER_TOKEN, data.token)
            handler.saveString(SharedPrefKeys.USER_FIRST_NAME, data.firstName)
            handler.saveString(SharedPrefKeys.USER_LAST_NAME, data.lastName)
            handler.saveString(SharedPrefKeys.USER_FULL_NAME, data.fullName)
            handler.saveString(SharedPrefKeys.USER_NAME, data.fullName)
            handler.saveString(SharedPrefKeys.USER_EMAIL, data.email)
            handler.saveBoolean(SharedPrefKeys.IS_LOGGED_IN, true)
            handler.saveBoolean(SharedPrefKeys.IS_EMAIL_VERIFIED, data.isEmailVerified)
        }

        @JvmStatic
        fun getLoginData(): LoginData {
            val loginData = LoginData()
            val handler = SharedPrefHandler.getInstance()

            loginData.userID = handler.getString(SharedPrefKeys.USER_ID, "")
            loginData.token = handler.getString(SharedPrefKeys.USER_TOKEN, "")
            loginData.firstName = handler.getString(SharedPrefKeys.USER_FIRST_NAME, "")
            loginData.lastName = handler.getString(SharedPrefKeys.USER_LAST_NAME, "")
            loginData.fullName = handler.getString(SharedPrefKeys.USER_FULL_NAME, "")
            loginData.userName = handler.getString(SharedPrefKeys.USER_NAME, "")
            loginData.email = handler.getString(SharedPrefKeys.USER_EMAIL, "")
            loginData.isLoggedIn = handler.getBoolean(SharedPrefKeys.IS_LOGGED_IN, false)
            loginData.isEmailVerified = handler.getBoolean(SharedPrefKeys.IS_EMAIL_VERIFIED, false)

            return loginData
        }

        @JvmStatic
        fun clear() {
            val handler = SharedPrefHandler.getInstance()

            handler.saveString(SharedPrefKeys.USER_ID, "")
            handler.saveString(SharedPrefKeys.USER_TOKEN, "")
            handler.saveString(SharedPrefKeys.USER_FIRST_NAME, "")
            handler.saveString(SharedPrefKeys.USER_LAST_NAME, "")
            handler.saveString(SharedPrefKeys.USER_FULL_NAME, "")
            handler.saveString(SharedPrefKeys.USER_NAME, "")
            handler.saveString(SharedPrefKeys.USER_EMAIL, "")
            handler.saveBoolean(SharedPrefKeys.IS_LOGGED_IN, false)
            handler.saveBoolean(SharedPrefKeys.IS_EMAIL_VERIFIED, false)
        }
    }
}