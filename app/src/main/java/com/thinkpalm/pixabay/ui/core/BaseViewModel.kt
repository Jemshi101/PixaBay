package com.thinkpalm.pixabay.ui.core

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thinkpalm.pixabay.model.core.MessageBean

/**
 * Created by Jemsheer K D on 02 February, 2019.
 * Package co.junkbot.joystick.core
 * Project Pixabay
 */
open class BaseViewModel : ViewModel() {
    var progressStatus = MutableLiveData<Boolean>()
    var showMessage = MutableLiveData<MessageBean>()

}