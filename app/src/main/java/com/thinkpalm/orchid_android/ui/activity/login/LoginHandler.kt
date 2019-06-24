package com.thinkpalm.orchid_android.ui.activity.login

import android.view.HapticFeedbackConstants
import android.view.View

/**
 * Created by Jemsheer K D on 21 June, 2019.
 * Package com.thinkpalm.orchid_android.ui.activity.login
 * Project Orchid
 */
class LoginHandler(var loginHandlerListener: LoginHandlerListener ) {

    fun performLogin(view: View) {
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
        //mVibrator.vibrate(25);
        loginHandlerListener.performLogin(view)
    }


    interface LoginHandlerListener {
        fun performLogin(view: View)

    }
}