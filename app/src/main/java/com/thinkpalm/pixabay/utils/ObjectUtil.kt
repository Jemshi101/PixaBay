package com.thinkpalm.pixabay.utils

import com.google.gson.Gson

/**
 * Created by Jemsheer K D on 10 March, 2019.
 * Package com.thinkpalm.pixabay.utils
 * Project Pixabay
 */
class ObjectUtil {
	companion object {

		inline fun <reified T> T.clone(obj: T): T {
			val gson = Gson()
			val json = gson.toJson(this)
			println(json)
			return gson.fromJson(json, T::class.java)
		}

	}
}
