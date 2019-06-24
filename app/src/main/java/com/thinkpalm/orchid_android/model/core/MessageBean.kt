package com.thinkpalm.orchid_android.model.core

import java.io.Serializable

/**
 * Created by Jemsheer K D on 18 January, 2019.
 * Package co.junkbot.joystick.models
 * Project RoyalArabianTours
 */
data class MessageBean(
	var message: String = "",
	var time: Int = 0,
	var type: Int = 0
) : Serializable {
	companion object {
		const val MESSAGE_TYPE_TOAST = 0
		const val MESSAGE_TYPE_SNACK_BAR = 1
	}
}