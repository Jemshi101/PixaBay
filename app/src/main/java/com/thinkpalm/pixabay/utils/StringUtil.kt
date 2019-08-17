package com.thinkpalm.pixabay.utils

import androidx.annotation.StringRes
import android.text.TextUtils
import com.thinkpalm.pixabay.core.App

class StringUtil {

	companion object {

		@JvmStatic
		fun trimLastSpace(mString: String?): String {
			return if (TextUtils.isEmpty(mString?.trim())) {
				""
			} else {
				if (!mString?.get(mString.length - 1)?.equals(" ")!!)
					mString else mString.substring(0, mString.length - 2)
			}
		}

		@JvmStatic
		fun getString(resID: Int): String {
			return try {
				App.getInstance().getString(resID)
			} catch (e: Exception) {
				return ""
			}
		}

		@JvmStatic
		fun getString(@StringRes resId: Int, vararg formatArgs: Any): String {
			return App.getInstance().getString(resId, *formatArgs)
		}


	}
}