package com.thinkpalm.orchid_android.ui.core

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thinkpalm.orchid_android.model.core.MessageBean

/**
 * Created by Jemsheer K D on 02 February, 2019.
 * Package co.junkbot.joystick.core
 * Project RoyalArabianTours
 */
open class BaseViewModel : ViewModel() {
    var progressStatus = MutableLiveData<Boolean>()
    var showMessage = MutableLiveData<MessageBean>()

}