package com.thinkpalm.orchid_android.ui.activity.login

import android.util.Patterns
import androidx.databinding.ObservableField
import com.thinkpalm.orchid_android.R
import com.thinkpalm.orchid_android.core.App
import com.thinkpalm.orchid_android.model.core.MessageBean
import com.thinkpalm.orchid_android.ui.core.BaseViewModel

/**
 * Created by Jemsheer K D on 21 June, 2019.
 * Package com.thinkpalm.orchid_android.ui.activity.login
 * Project Orchid
 */
class LoginViewModel : BaseViewModel() {
    var email = ObservableField<String>("")
    var password = ObservableField<String>("")

    fun performLogin() {

        if (validateLogin()) {

        }

    }

    private fun validateLogin(): Boolean {

        if (!Patterns.EMAIL_ADDRESS.matcher(email.get()).matches()) {

            showMessage.value = MessageBean(App.getInstance().getString(R.string.message_please_enter_a_valid_email_address))
            return false
        }

        return true
    }
}